
## 小技巧
### 升级版本号
    //指定版本号
    mvn versions:set -DnewVersion=3.1.1-SNAPSHOT 或者  mvn versions:set -DnewVersion=3.1.1-RELEASE
    //回滚
    mvn versions:revert
    //提交
    mvn versions:commit

|参数 |默认值 |说明 | 
---|---|---
|allowSnapshots |false |是否更新-snapshot快照版| 
|artifactId |${project.artifactId} |指定artifactId | 
|generateBackupPoms |true |是否备份pom文件 | 
|groupId |${project.groupId} |指定groupId | 
|newVersion | |设置的新版本号 | 
|nextSnapshot |false |更新版本号为下一个快照版本号| 
|oldVersion |${project.version} |指定需要更新的版本号可以使用缺省‘*’| 
|processAllModules |false |是否更新目录下所有模块无论是否声明父子节点| 
|processDependencies |true |是否更新依赖其的版本号| 
|processParent |true |是否更新父节点的版本号| 
|processPlugins |true |是否更新插件中的版本号| 
|processProject |true |是否更新模块自身的版本号| 
|removeSnapshot |false |移除snapshot快照版本，使之为release稳定版| 
|updateMatchingVersions |true |是否更新在子模块中显式指定的匹配版本(如/项目/版本)。|

### docker redis
    docker run --name redis1 -p 6379:6379 -d -v $PWD/redis:/data --restart=always redis:5.0.5 redis-server --appendonly yes --requirepass "123456"
