# JoinQuickSDK

#### 介绍
H5项目打包成APK并对接QuickSDK,由于使用了dcloud的plus的SDK，导致需要继承2个Application，而同时接2个Application又会导致DEX文件超过65K而导致quick无法分包，所以又使用了Multidex对DEX文件进行拆分，所以同时使用了三个Application。plusSDK在本项目中主要用来进行JS和android通信，其它功能没有用到，有点累赘，最好的方法是使用原生的自己构建JS和android通信，就能避免使用plusSDK，也就不会导致dex超过65K了

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
