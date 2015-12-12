1:搭建开发环境
	1.1: 导入开发包
			BeanUtils开发包需要commons-logging.jar的支持
			mysql驱动开发包
			Dbutils开发包
			//Log4j开发包
			c3p0数据库链接管理开发包(以及c3p0对数据库初始化的xml的配置文件)
			commons-fileupload-1.2.1支持文件上传的开发包需要commons-io-1.4的支持
			commons-io-1.4用于补充commons-io-1.4
	1.2: 创建组织程序的包
			com.dph.domain (存放实体)
			com.dph.dao	(存放实现数据库操作的抽象接口)
			com.dph.dao.impl (存放实现了数据库操作的接口的实现类)
			com.dph.service (存放了服务层的抽象接口)
			com.dph.service.impl (存放了所有实现服务抽象接口的实现类)
			com.dph.web.controller (存放处理请求的servlet)
			com.dph.web.UI (存放给用户提供的用户界面)
			com.dph.utils (存放了工程所需要的自己封装的工具类)
			com.dph.test (存放了工程测试的类)
			
			WEB-INF/jsp (用于保存网站所有的jsp，应为这些都是servlet传给的数据才能显示，
						   直接调用没有数据，所以隐藏不能直接浏览.)
	1.3:	创建数据库
				1.31创建名称为excellent_course的数据库
					create database excellent_course character set utf8 collate utf8_general_ci;
				1.32 创建t_user表
					create table t_user
					(
						id varchar(40) primary key,
						username varchar(15) not null unique,
						password varchar(32) not null,
						gender varchar(3) not null,
						birthday date null,
						email varchar(50) NOT NULL ,
						nickname varchar(20) NOT NULL ,
						description varchar(100)	
					);
				1.32 创建t_user表
					
			
			