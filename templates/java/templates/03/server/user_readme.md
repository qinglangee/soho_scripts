# 运行
`mvn spring-boot:run`

# 修改内容
1. groupId  artifactId
2. 数据库
3. 表名
4. regenerate.cmd

# 生成代码
配置好后执行 `mvn mybatis-generator:generate` 



# 新加内容

## pom.xml
```xml
    <dependencies>
		<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
			<version>1.2.8.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.mybatis.generator</groupId>
				<artifactId>mybatis-generator-maven-plugin</artifactId>
				<version>1.3.6</version>
				<configuration>
					<verbose>true</verbose>
					<overwrite>true</overwrite>
				</configuration>
			</plugin>
		</plugins>
	</build>
```
## 文件

application.yml  
generatorConfig.xml
generatorJdbc.properties

regenerate.cmd