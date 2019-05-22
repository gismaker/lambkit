##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage};

import com.jfinal.core.NotAction;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.base.ResultJson;
import com.lambkit.common.base.ResultKit;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.db.sql.condition.SqlBuilder;
import com.lambkit.web.controller.BaseController;

public abstract class MschController<T extends Model<T>> extends BaseController {

	protected abstract String getTableName();
	protected abstract T findById(Object id);
	protected abstract String getTemplatePath();
	
	private String templatePath() {
		return "data/" + getTemplatePath();
	}
	/**
	 * 数据详细页
	 */
	public void index() {
		MgrTable tbc = getTableView(getTableName(), true);
		Integer id = getParaToInt(0) == null ? getParaToInt("id") : getParaToInt(0);
		keepTable(tbc, findById(id));
		render(templatePath() + "index.html");
	}
	
	/**
	 * 单条数据
	 */
	public void get() {
		getTableView(getTableName(), true);
		Integer id = getParaToInt(0) == null ? getParaToInt("id") : getParaToInt(0);
		T datas = findById(id);
		if(datas==null) {
			renderJson(new ResultJson(0, "fail", null));
		} else {
			renderJson(new ResultJson(1, "success", datas));
		}
	}
	
	/**
	 * 数据列表页
	 */
	public void list() {
		getTableDefault(getTableName(), true);
		render(templatePath() + "list.html");
	}
	
	/**
	 * 数据列表页
	 */
	public void view() {
		getTableDefault(getTableName(), true);
		render(templatePath() + "view.html");
	}
	
	/**
	 * 分页
	 */
	public void page() {
		MgrTable tbc = getTableView(getTableName(), true);
		if (tbc == null) {
			renderJson(new ResultJson(0, "fail", "table config is null"));
			return;
		}
		ConditionBuilder cb = getConditionsSQL(tbc).build("");
		SqlBuilder sb = new SqlBuilder();
		String select = sb.appendSelect(tbc).build();
		sb.clear().appendFrom(tbc).appendConditions(cb);
		//排序
		String orderby = getPara("orderby");
		if(StrKit.notBlank(orderby)) sb.appendOrderBy(orderby);
		String sql = sb.build();
		Integer pNumber = getParaToInt("pageNum", getParaToInt("page", 1));
		Integer pSize =  getParaToInt("numPerPage", getParaToInt("limit", 15));
		Page<Record> page = Db.paginate(pNumber, pSize, select, sql, cb.getSqlParas());
		String type = getPara("rt", "default");
		if("default".equalsIgnoreCase(type)) {
			renderJson(ResultKit.json(1, "success", page));
		} else {
			renderJson(ResultKit.page(type, 1, "success", page.getTotalRow(), page.getList()));
		}
	}
	
	public void add() {
		render(templatePath() + "add.html");
	}
	
	public void edit() {
		getTableView(getTableName(), true);
		Integer id = getParaToInt(0) == null ? getParaToInt("id") : getParaToInt(0);
		setAttr("model", findById(id));
		render(templatePath() + "add.html");
	}
	
	@NotAction
	protected void doSave(T env) {
		boolean flag = env.save();
		if(flag) {
			renderJson(new ResultJson(1, "success", env));
		} else {
			renderJson(new ResultJson(0, "fail", "失败"));
		}
	}
	
	@NotAction
	protected void doUpdate(T env) {
		boolean flag = env.update();
		if(flag) {
			renderJson(new ResultJson(1, "success", env));
		} else {
			renderJson(new ResultJson(0, "fail", "失败"));
		}
	}
	
	public void delete() {
		boolean flag = false;
		Integer id = getParaToInt(0) == null ? getParaToInt("id") : getParaToInt(0);
		T datas = findById(id);
		if(datas!=null) {
			flag = datas.delete();
		}
		if(flag) {
			renderJson(new ResultJson(1, "success", "deleted"));
		} else {
			renderJson(new ResultJson(0, "fail", "deleted"));
		}
	}
}
