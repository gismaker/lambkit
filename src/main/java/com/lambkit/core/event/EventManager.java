/**
 * Copyright (c) 2015-2020, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.core.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.jfinal.core.JFinal;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.lambkit.Lambkit;
import com.lambkit.common.LambkitConfig;
import com.lambkit.common.util.ClassUtils;
import com.lambkit.core.event.annotation.Listener;

public class EventManager {

	private final ExecutorService threadPool;
	private final Map<String, List<EventListener>> asyncListenerMap;
	private final Map<String, List<EventListener>> listenerMap;
	private static final Log log = Log.getLog(EventManager.class);

	private static EventManager manager;

	private EventManager() {
		//threadPool = Executors.newFixedThreadPool(5);
		threadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.MINUTES,
                new SynchronousQueue<Runnable>());
		asyncListenerMap = new ConcurrentHashMap<String, List<EventListener>>();
		listenerMap = new ConcurrentHashMap<String, List<EventListener>>();
		initListeners();
	}

	public static EventManager me() {
		if(manager==null) {
			manager = new EventManager();
		}
		return manager;
	}
	
	private void initListeners() {
		LambkitConfig config = Lambkit.getLambkitConfig();
		String packages = config.getAutoRegisterEventPackages();
		if(StrKit.isBlank(packages)) {
			packages = "com.lambkit";
		}
        Set<Class<?>> classes = ClassUtils.scanPackageBySuper(packages, true, EventListener.class);
        if (classes.size()==0) {
            return;
        }
        for (Class<?> clazz : classes) {
        	//System.out.println(clazz.getName());
            registerListener((Class<? extends EventListener>)clazz);
        }
    }

	public void unRegisterListener(Class<? extends EventListener> listenerClass) {

		deleteListner(listenerMap, listenerClass);
		deleteListner(asyncListenerMap, listenerClass);
		
		if (JFinal.me().getConstants().getDevMode()) {
			//System.out.println(String.format("listener[%s]-->>unRegisterListener.", listenerClass));
		}

	}

	private void deleteListner(Map<String, List<EventListener>> map, Class<? extends EventListener> listenerClass) {
		for (Map.Entry<String, List<EventListener>> entry : map.entrySet()) {
			EventListener deleteListener = null;
			for (EventListener listener : entry.getValue()) {
				if (listener.getClass() == listenerClass) {
					deleteListener = listener;
				}
			}
			if (deleteListener != null) {
				entry.getValue().remove(deleteListener);
			}
		}
	}

	public void registerListener(Class<? extends EventListener> listenerClass) {

		if (listenerClass == null) {
			return;
		}

		Listener listenerAnnotation = listenerClass.getAnnotation(Listener.class);
		if (listenerAnnotation == null) {
			log.warn("listenerClass[" + listenerAnnotation + "] resigter fail,because not use Listener annotation.");
			return;
		}

		String[] actions = listenerAnnotation.action();
		if (actions == null || actions.length == 0) {
			log.warn("listenerClass[" + listenerAnnotation + "] resigter fail,because action is null or blank.");
			return;
		}

		if (listenerHasRegisterBefore(listenerClass)) {
			return;
		}

		EventListener listener = newListener(listenerClass);
		if (listener == null) {
			return;
		}

		for (String action : actions) {
			List<EventListener> list = null;
			if (listenerAnnotation.async()) {
				list = asyncListenerMap.get(action);
			} else {
				list = listenerMap.get(action);
			}
			if (null == list) {
				list = new ArrayList<EventListener>();
			}
			if (list.isEmpty() || !list.contains(listener)) {
				list.add(listener);
			}
			Collections.sort(list, new Comparator<EventListener>() {
				@Override
				public int compare(EventListener o1, EventListener o2) {
					Listener l1 = o1.getClass().getAnnotation(Listener.class);
					Listener l2 = o2.getClass().getAnnotation(Listener.class);
					return l1.weight() - l2.weight();
				}
			});

			if (listenerAnnotation.async()) {
				asyncListenerMap.put(action, list);
			} else {
				listenerMap.put(action, list);
			}
		}
		
		if (JFinal.me().getConstants().getDevMode()) {
			//System.out.println(String.format("listener[%s]-->>registered.", listener));
		}

	}

	private EventListener newListener(Class<? extends EventListener> listenerClass) {
		EventListener listener = null;
		try {
			listener = listenerClass.newInstance();
		} catch (Throwable e) {
			log.error(String.format("listener \"%s\" newInstance is error. ", listenerClass), e);
		}
		return listener;
	}

	private boolean listenerHasRegisterBefore(Class<? extends EventListener> listenerClass) {
		 return findFromMap(listenerClass, listenerMap)
	                || findFromMap(listenerClass, asyncListenerMap);
	}
	
	private boolean findFromMap(Class<? extends EventListener> listenerClass, Map<String, List<EventListener>> map) {
        for (Map.Entry<String, List<EventListener>> entry : map.entrySet()) {
            List<EventListener> listeners = entry.getValue();
            if (listeners == null || listeners.isEmpty()) {
                continue;
            }
            for (EventListener ml : listeners) {
                if (listenerClass == ml.getClass()) {
                    return true;
                }
            }
        }
        return false;
    }

	public void pulish(final Event message) {
		String action = message.getAction();

		List<EventListener> syncListeners = listenerMap.get(action);
		if (syncListeners != null && !syncListeners.isEmpty()) {
			invokeListeners(message, syncListeners);
		}

		List<EventListener> listeners = asyncListenerMap.get(action);
		if (listeners != null && !listeners.isEmpty()) {
			invokeListenersAsync(message, listeners);
		}

	}

	private void invokeListeners(final Event message, List<EventListener> syncListeners) {
		for (final EventListener listener : syncListeners) {
			try {
				if (JFinal.me().getConstants().getDevMode()) {
					//System.out.println(String.format("listener[%s]-->>onMessage(%s)", listener, message));
				}
				listener.onEvent(message);
			} catch (Throwable e) {
				log.error(String.format("listener[%s] onMessage is erro! ", listener.getClass()), e);
			}
		}
	}

	private void invokeListenersAsync(final Event message, List<EventListener> listeners) {
		for (final EventListener listener : listeners) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						if (JFinal.me().getConstants().getDevMode()) {
							//System.out.println(String.format("listener[%s]-->>onMessage(%s) in async", listener, message));
						}
						listener.onEvent(message);
					} catch (Throwable e) {
						log.error(String.format("listener[%s] onMessage is erro! ", listener.getClass()), e);
					}
				}
			});
		}
	}

}
