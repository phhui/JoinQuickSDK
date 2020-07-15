# JoinQuickSDK

#### 介绍
H5项目打包成APK并对接QuickSDK,由于使用了dcloud的plus的SDK，导致需要继承2个Application，而同时接2个Application又会导致DEX文件超过65K而导致quick无法分包，所以又使用了Multidex对DEX文件进行拆分，所以同时使用了三个Application。

#### 简要说明
plusSDK在本项目中主要用来进行JS和android通信，其它功能没有用到，有点累赘，最好的方法是使用原生的自己构建JS和android通信，就能避免使用plusSDK，也就不会导致dex超过65K了


#### 使用说明

MainApplication.java,NPandoraEntry.java,NPandoraEntryActivity.java三个文件为dcloud.plusSDK的初始页，换成自己的项目的话则不需要这三项
QuickUtil.java，IQuickListener.java,QuickParam.java为我简单封闭的QuickSDK使用类，自己的项目直接使用即可,无需其它文件。
ApplicationImpl.java,ApplicationListener.java,ProxyApplication.java这三个是为了实现同时继承多个Application而实现的反射代理类

