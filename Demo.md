````### 练习总结
SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp

SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp where emp_name = 'abc' OR '1' = '1'

SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp where emp_name = 'abc\' OR \'1\' = \'1'

SELECT count(*) from t_emp

SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_id = 1

SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_age > 25

INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES()

UPDATE t_emp SET emp_salary = ? WHERE emp_id = ?

DELETE FROM t_emp WHERE emp_id = ?

-- 查看当前连接，书屋的提交方式 （ON 自动提交、OFF 关闭自动提交，需要手动提交)
SHOW VARIABLES LIKE 'autocommit'
-- 0、false 都是设置当前连接的事务提交方式为手动提交
SET autocommit = TRUE;
-- SET autocommit = TRUE;

-- 场景：zhangsan给lisi转装100元，先zhangsan扣钱，lisi加钱
UPDATE t_bank SET money = money - 100 WHERE id = 1;
UPDATE t_bank SET money = money + 100 WHERE id = 2;


-- 提交事务：让当前连接的操作，提交，对数据库产生影响，持久性的影响
COMMIT;

-- 回滚事务：让当前连接的操作，回滚到数据修改前的状态！
ROLLBACK;

-- 继续在atguigu的库中创建银行表
SELECT * FROM t_bank;

CREATE TABLE t_bank(
id INT PRIMARY KEY AUTO_INCREMENT COMMENT '账号主键',
account VARCHAR(20) NOT NULL UNIQUE COMMENT '账号',
money  INT UNSIGNED COMMENT '金额，不能为负值') ;

INSERT INTO t_bank(account,money) VALUES
('zhangsan',1000),('lisi',1000);

-- 查看数据库的事务隔离级别
show variables like 'transaction%';

-- 查看数据库的
show engines;
show variables like '%storage_engine%';

-- 查看建表语句
show create table t_bank;

-- 修改表的存储引擎
alter table t_bank engine=innodb;

### 补充
如何查看MySQL的当前存储引擎

一般情况下，mysql会默认提供多种存储引擎,你可以通过下面的查看:

看你的mysql现在已提供什么存储引擎:

```mysql
mysql> show engines;
```

看你的mysql当前默认的存储引擎:

```mysql
mysql> show variables like '%storage_engine%';
```

你要看某个表用了什么引擎(在显示结果里参数engine后面的就表示该表当前用的存储引擎):

```mysql
mysql> show create table 表名;
```

如何查看Mysql服务器上的版本

额 系统函数啊

```mysql
select version();
```

代码才帅气

Mysql数据库3种存储引擎有什么区别？

这个是考虑性能的问题，还有事务的支持，吧  百度一下你就知道

MyISAM、InnoDB、Heap(Memory)、NDB

貌似一般都是使用  InnoDB的，

mysql的存储引擎包括：MyISAM、InnoDB、BDB、MEMORY、MERGE、EXAMPLE、NDBCluster、ARCHIVE、CSV、BLACKHOLE、FEDERATED等，其中InnoDB和BDB提供事务安全表，其他存储引擎都是非事务安全表。

最常使用的2种存储引擎:

1.Myisam是Mysql的默认存储引擎，当create创建新表时，未指定新表的存储引擎时，默认使用Myisam。每个MyISAM在磁盘上存储成三个文件。文件名都和表名相同，扩展名分别是.frm（存储表定义）、.MYD(MYData，存储数据)、.MYI(MYIndex，存储索引)。数据文件和索引文件可以放置在不同的目录，平均分布io，获得更快的速度。

2.InnoDB存储引擎提供了具有提交、回滚和崩溃恢复能力的事务安全。但是对比Myisam的存储引擎，InnoDB写的处理效率差一些并且会占用更多的磁盘空间以保留数据和索引。

修改mysql的默认存储引擎

1、查看mysql存储引擎命令，

在mysql>提示符下搞入

```mysql
show engines;
```

字段 Support为:Default表示默认存储引擎

www.2cto.com

2、设置InnoDB为默认引擎：

在配置文件my.cnf中的 [mysqld] 下面加入

default-storage-engine=INNODB 一句

3、重启mysql服务器：

```mysql
mysqladmin -u root -p shutdown
```

或者service mysqld restart 登录mysql数据库

MySQL查看和修改表的存储引擎

1 查看系统支持的存储引擎

```mysql
show engines;
```

2 查看表使用的存储引擎

两种方法：

```mysql
a、show table status from db_name where name='table_name';
b、show create table table_name;
```

如果显示的格式不好看，可以用\g代替行尾分号

有人说用第二种方法不准确，我试了下，关闭掉原先默认的Innodb引擎后根本无法执行show create table table_name指令，因为之前建的是Innodb表，关掉后默认用MyISAM引擎，导致Innodb表数据无法被正确读取。

3 修改表引擎方法

```mysql
alter table `table_name` engine=innodb;
```

4 关闭Innodb引擎方法

关闭mysql服务：

```mysql
 net stop mysql
```

找到mysql安装目录下的my.ini文件：

找到default-storage-engine=INNODB 改为default-storage-engine=MYISAM

找到#skip-innodb 改为skip-innodb

启动mysql服务：

```mysql
net start mysql
```
````