id文件夹是25w个id的切片。从t0-t24 每个约1w。先商量好爬哪几个。 有个test.txt里面10个数据，可以用来测试。

目前还只是测试了10条数据的demo。跑起来没问题，也挺快的。多了不知道会不会出现什么bug

1.新建工程。lib下有commons-codec-1.3.jar，添加到项目的buildpath
2.导入src下的4个java文件
3.修改XMLDownloader.java(原来sample code里的那个itemsearch.java)里面的秘钥成自己的。
4.修改Main.java下面各个路径。我图方便用的绝对路径，你们改成自己的
5.运行Main.java