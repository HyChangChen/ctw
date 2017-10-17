CREATE FUNCTION `getOrgChildLst` (orgid INT) RETURNS VARCHAR (1000)
BEGIN
	DECLARE
		sTemp VARCHAR (1000);
DECLARE
	sTempChd VARCHAR (1000);
SET sTemp = '$';
SET sTempChd = cast(orgid AS CHAR);
WHILE sTempChd IS NOT NULL DO
SET sTemp = concat(sTemp, ',', sTempChd);
SELECT
	group_concat(id) INTO sTempChd
FROM
	t_sys_org
WHERE
	FIND_IN_SET(partent_id, sTempChd) > 0;
END
WHILE;
RETURN sTemp;
END