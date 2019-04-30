##将项目或jar发布到中央仓库
####参考
https://www.jdon.com/idea/publish-to-center-maven.html

https://blog.csdn.net/huangjinjin520/article/details/78915789

https://segmentfault.com/a/1190000009450347

https://blog.csdn.net/u010479969/article/details/53234469
####具体步骤
　　Sonatype的开源软件存储库托管（OSSRH）服务是项目所有者和贡献者将其组件发布到中央存储库的主要途径。它是Nexus Repository Manager的托管部署，其中 Nexus Staging Suite 用于部署过程和验证，并与Maven中央仓库内容交付网络的同步过程相结合。

　　也就是说，我们需要把我们的项目发布到OSSRH，然后使用其Nexus Staging Suite实现部署和验证，发布成功后，就会和中央仓库同步。

下面是先决条件：

第1步：  确保您是否安装了JDK，Maven，Github等。

第2步： 创建Github帐户（如果尚未创建）。

第3步：  创建一个新的Github存储库。

第4步： 向您的Github帐户添加新的SSH密钥

第5步：  将代码推送到Github。 （以上步骤是确保你项目在Github，如果已经有可忽视）

第6步：  注册Sonatype Jira帐户。记住你的用户名和密码，后面需要在maven的settings.xml中设置

https://oss.sonatype.org/ 和 https://issues.sonatype.org/login.jsp
注册邮箱：xj**7**3@163.com ；(real name)用户名：xjch；密码：X&1*253*&j**；

第7步： 为新项目托管创建Jira问题。点击此处获取样品申请，也就是为你的项目提交一个说明：
```
项目的分组名称
GroupId：com.github.xjch
访问项目的URL，写仓库地址 
ProjectURL：https://github.com/xjch/nexus-oss
访问项目的URL，写仓库地址 
SCMurl：https://github.com/xjch/nexus-oss.git
```
注意其中groupId设置比较重要，决定了别人使用你的库包时的pom.xml中groupId。具体申请结果请查看 https://issues.sonatype.org/browse/OSSRH-48196
```
com.github.xjch has been prepared, now user(s) xjch can:
Deploy snapshot artifacts into repository 
https://oss.sonatype.org/content/repositories/snapshots

Deploy release artifacts into the staging repository 
https://oss.sonatype.org/service/local/staging/deploy/maven2

Promote staged artifacts into repository 'Releases'
Download snapshot and release artifacts from group 
https://oss.sonatype.org/content/groups/public

Download snapshot, release and staged artifacts from staging group 
https://oss.sonatype.org/content/groups/staging

please comment on this ticket when you promoted your first release, thanks
```
第8步：安装GNU PG。  

Windows系统直接去https://www.gpg4win.org/download.html下载Gpg4win，如果是Linux，可以通过yum install gpg安装。在您的操作系统中安装并验证如下：
```
gpg --version

gpg (GnuPG) 2.2.15
libgcrypt 1.8.4
Copyright (C) 2019 Free Software Foundation, Inc.
License GPLv3+: GNU GPL version 3 or later <https://gnu.org/licenses/gpl.html>
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

Home: C:/Users/xjch/AppData/Roaming/gnupg
Supported algorithms:
Pubkey: RSA, ELG, DSA, ECDH, ECDSA, EDDSA
Cipher: IDEA, 3DES, CAST5, BLOWFISH, AES, AES192, AES256, TWOFISH,
        CAMELLIA128, CAMELLIA192, CAMELLIA256
Hash: SHA1, RIPEMD160, SHA256, SHA384, SHA512, SHA224
Compression: Uncompressed, ZIP, ZLIB, BZIP2
```
出现版本等信息就安装成功了，注意Home: C:/Users/xjch/AppData/Roaming/gnupg,在pom和setting文件中需要设置

第9步： 生成密钥对。
```
gpg --full-gen-key
```
提示输入：

Real name：xujch (需要至少5个字符)

Email address：xj**7**3@163.com

根据提示输入大写O确认无误

第10步: 弹出一个输入密码的对话框，输入passphrase，请务必记住passphrase(加密密钥): xu***&7**3，在后面Maven的settings.xml中需要用到，包括deploy发布时，需要输入这个值。

第11步:设置好加密密钥后，需要发布到OSSRH服务器上，因为，你会使用这个密钥加密你的jar包，当你上传你的jar包到OSSRH服务器时，它就会用它来解密。查看秘钥：
```
gpg --list-key

pub   rsa2048 2019-04-29 [SC]
      F900AE1C985C806377464FD6A2A1BDD694EDD34F
uid           [ultimate] xujch (first try) <xjch7703@163.com>
sub   rsa2048 2019-04-29 [E]
```
这里 F900AE1C985C806377464FD6A2A1BDD694EDD34F 就是你的key的Id，
```
gpg --keyserver hkp://pool.sks-keyservers.net --send-keys F900AE1C985C806377464FD6A2A1BDD694EDD34F
gpg --keyserver hkp://keyserver.ubuntu.com:11371 --send-keys F900AE1C985C806377464FD6A2A1BDD694EDD34F
```
这样就把你的密钥上传到pool.sks-keyservers.net ，但是具体服务器需要根据当时OSSRH提示，如果在后面发布时，它提示你没有上传密钥到http://keyserver.ubuntu.com:11371/，那么你就要上传密钥到这个服务器：
查询公钥是否发布成功
```
gpg --keyserver hkp://pool.sks-keyservers.net --recv-keys F900AE1C985C806377464FD6A2A1BDD694EDD34F
gpg: key A2A1BDD694EDD34F: "xujch (first try) <xjch7703@163.com>" not changed
gpg: Total number processed: 1
gpg:              unchanged: 1

gpg --keyserver hkp://keyserver.ubuntu.com:11371 --recv-keys F900AE1C985C806377464FD6A2A1BDD694EDD34F
gpg: key A2A1BDD694EDD34F: "xujch (first try) <xjch7703@163.com>" not changed
gpg: Total number processed: 1
gpg:              unchanged: 1
```
第12步:修改Maven配置文件，需要同时修改全局的setting.xml和项目的pom.xml文件，如setting.xml文件中增加如下内容
```
<server>
    <id>ossrh</id>
    <username>xjch</username>
    <password>X&amp;1**5**&amp;j**</password>
</server>
<profile>
    <id>ossrh</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
        <gpg.executable>gpg</gpg.executable>
        <gpg.passphrase>xu***&amp;7**3</gpg.passphrase>
        <gpg.executable>C:\Program Files (x86)\Gpg4win\..\GnuPG\bin\gpg.exe</gpg.executable>
        <gpg.homedir>C:/Users/xjch/AppData/Roaming/gnupg</gpg.homedir>
        <keyname>F900AE1C985C806377464FD6A2A1BDD694EDD34F</keyname>
    </properties>
</profile>
```
因passphrase中带有特殊字符&，在setting.xml中需要使用转移字符\&amp;


在pom文件中引入插件时，需要手动下载插件
```
mvn dependency:get -DrepoUrl=http://repo.maven.apache.org/maven2/ -Dartifact=org.apache.maven.plugins:maven-gpg-plugin:1.6
mvn dependency:get -DrepoUrl=http://repo.maven.apache.org/maven2/ -Dartifact=org.apache.maven.plugins:maven-source-plugin:3.0.1
mvn dependency:get -DrepoUrl=http://repo.maven.apache.org/maven2/ -Dartifact=org.sonatype.plugins:nexus-staging-maven-plugin:1.6.7

```
第13步:上传jar包到仓库
第一次deploy后反馈的如下三个错误：

没有javadoc 使用 mvn javadoc:jar 生成
```
Event: Failed: Javadoc Validation
Monday, April 29, 2019 20:09:53 (GMT+0800)
typeId	javadoc-staging
failureMessage	Missing: no javadoc jar found in folder '/com/github/xjch/common-response/1.0.0'
```
没有java source 使用 mvn source:jar-no-fork 生成
```
Event: Failed: Sources Validation
Monday, April 29, 2019 20:10:55 (GMT+0800)
typeId	sources-staging
failureMessage	Missing: no sources jar found in folder '/com/github/xjch/common-response/1.0.0'
```
没有对jar和pom文件进行签名，执行命令 没有对jar和pom文件进行签名，执行命令 mvn verify gpg:sign install:install 输入密码xu***&7**3
```
Event: Failed: Signature Validation
Monday, April 29, 2019 20:10:47 (GMT+0800)
typeId	signature-staging
failureMessage	Missing Signature: '/com/github/xjch/common-response/1.0.0/common-response-1.0.0.jar.asc' does not exist for 'common-response-1.0.0.jar'.
failureMessage	Missing Signature: '/com/github/xjch/common-response/1.0.0/common-response-1.0.0.pom.asc' does not exist for 'common-response-1.0.0.pom'.
```
最终使用一条组合命令，完成上传
```
mvn clean source:jar-no-fork javadoc:jar verify gpg:sign install:install deploy:deploy
```

第14步:浏览到https://oss.sonatype.org/index.html#stagingRepositories，管理你的发布上传的项目，如果这个网址没有，这是进入https://oss.sonatype.org/以后，寻找Staging Repositories
右边会出现很多列表，如何找到自己的项目？在列表右上角搜索中输入你的项目关键字，如xjch，
选择项目后，再点按Close，就完成Release，或者再按一次Release按钮就完成发布了。在Staging Profiles可以看到正在release。

通知 Sonatype 构件已成功发布。这个前面的Sonatype工作人员其实在审核你的Issue时，在comment中已经提示你了，在Issue下面回复一条“构件已成功发布”的评论，这是为了通知 Sonatype 的工作人员为需要发布的构件做审批，发布后会关闭该Issue。

第15步:访问：https://repo.maven.apache.org/maven2，如果你的项目是com.github.xjch格式，那么进入com，再进入github,在进入xjch，应该能发现你发布的版本,或者到一个新的Maven环境下，在pom.xml中加入你的新版本配置以后，进行mvn install等操作，是否拉取成功.

中央仓库搜索网站：http://search.maven.org/,可以在maven的中央仓库中搜索到自己发布的构件了

第16步:以后的发布流程：

a）构件完成后直接使用maven在命令行上传构建；

b）在https://oss.sonatype.org/close并release构件；

c)等待同步好（大约2小时多）之后，就可以使用了