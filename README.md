# migration

[![Java CI with Maven](https://github.com/JavaDream/migration/actions/workflows/maven.yml/badge.svg?branch=kotlin)](https://github.com/JavaDream/migration/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=JavaDream_migration&metric=alert_status&branch=kotlin)](https://sonarcloud.io/dashboard?id=JavaDream_migration&branch=kotlin)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=JavaDream_migration&metric=code_smells&branch=kotlin)](https://sonarcloud.io/dashboard?id=JavaDream_migration&branch=kotlin)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=JavaDream_migration&metric=coverage&branch=kotlin)](https://sonarcloud.io/dashboard?id=JavaDream_migration&branch=kotlin)


Kotlin/Java 项目的数据migration工具

## 为什么要有这个项目

本来java里面已经有了flyway, liquibase做数据的脚本迁移，但是这些竟然部分功能是要收费的。例如 flyway undo的这么基础的功能都是要给钱的，还不便宜。
另外，这些工具还不如rails强大，是写原生的数据库的脚本来生成数据库的迁移，当数据库不同的时候，大家就需要掌握不同的语法，这是一个费劲的事情。不如rails里面的db migration方便。
所以，我决定重新发明这个轮子。

## 项目目标
1. 开源，免费，永远的免费。
2. 使用java简单的语法来处理数据库的构建，而不是使用原生的sql。
3. 支持SpringBoot, Quarkus等Java框架。
4. 支持Kotlin更简便的语法。
5. 支持多种数据库，Mysql，Postgresql等。

## 使用

```kotlin
createTable("test_table") {
    option id true id ColumnType.String
    option options "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"

    column bigInt "bigint_column"
    column string "string_column" limit 20
}
```


