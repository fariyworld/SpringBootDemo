所用到的技术要点：
1.oracle
    1.1、oracle序列详解和创建自增主键
    -- 创建序列
    CREATE sequence seq_mace_dept_id
    	START WITH 3
    	INCREMENT BY 1
    	minvalue 3
    	maxvalue 9999999
    	nocycle;

    SELECT seq_mace_dept_id.CURRVAL from dual;

    -- 创建触发器
    create or replace trigger trigger_mace_dept_increase_id
      before insert on mace.DEPT   --mace.DEPT 是表名
      for each row
    declare
      nextid number;
    begin
      IF :new.ID IS NULL or :new.ID=0 THEN --ID是列名
        select seq_mace_dept_id.nextval    --seq_mace_dept_id正是刚才创建的序列
        into nextid
        from sys.dual;
        :new.ID:=nextid;
      end if;
    end trigger_mace_dept_increase_id;

    1.2、在oracle insert 后返回主键
    <selectKey resultType="java.lang.Integer" keyProperty="ID" order="BEFORE" >
        select seq_mace_dept_id.nextval from dual
    </selectKey>