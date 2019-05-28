#namespace("upms")

#sql("selectUpmsPermissionByUpmsUserId")
SELECT
  permission_id, system_id, pid, name, type, permission_value, uri, icon, status, ctime, orders
from upms_permission up where up.status=1 and up.permission_id in (
			select permission_id from upms_role_permission urp where urp.role_id in (
				select uur.role_id role_id from upms_user_role uur where uur.user_id=#para(0)
			)
			union
			select permission_id from upms_user_permission uup1 where uup1.user_id=#para(0) and uup1.type=1
		)
		and up.permission_id not in (
			select permission_id from upms_user_permission uup2 where uup2.user_id=#para(0) and uup2.type=-1
		) order by up.orders asc
#end

#sql("selectUpmsRoleByUpmsUserId")
SELECT
  role_id, name, title, description, ctime, orders
from upms_role ur where ur.role_id in (
			select uur.role_id from upms_user_role uur where uur.user_id=#para(0)
		)
#end

#end