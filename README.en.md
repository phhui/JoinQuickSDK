# JoinQuickSDK

#### Description
H5项目打包成APK并对接QuickSDK,由于使用了dcloud的plus的SDK，导致需要继承2个Application，而同时接2个Application又会导致DEX文件超过65K而导致quick无法分包，所以又使用了Multidex对DEX文件进行拆分，所以同时使用了三个Application。plusSDK在本项目中主要用来进行JS和android通信，其它功能没有用到，有点累赘，最好的方法是使用原生的自己构建JS和android通信，就能避免使用plusSDK，也就不会导致dex超过65K了

#### Software Architecture
Software architecture description

#### Installation

1.  xxxx
2.  xxxx
3.  xxxx

#### Instructions

1.  xxxx
2.  xxxx
3.  xxxx

#### Contribution

1.  Fork the repository
2.  Create Feat_xxx branch
3.  Commit your code
4.  Create Pull Request


#### Gitee Feature

1.  You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2.  Gitee blog [blog.gitee.com](https://blog.gitee.com)
3.  Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4.  The most valuable open source project [GVP](https://gitee.com/gvp)
5.  The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6.  The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
