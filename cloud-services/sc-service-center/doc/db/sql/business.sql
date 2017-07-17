-- 获取顶级公司
SELECT sso.company_code, sso.parent_company_code ,ssc2.company_level,ssc2.company_name_cn 
FROM sfxie_sys_company ssc
INNER JOIN sfxie_sys_organization sso  ON sso.parent_company_code = ssc.company_code
INNER JOIN sfxie_sys_company ssc2 ON ssc2.company_code = sso.company_code
WHERE ssc.company_level = -1;