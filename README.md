### 背景
作为一个客户端开发人员，每次后端开发完成之前都在“盲打”代码，凭自己想象的数据来写页面。因此就有了写一个自动mock数据脚本的想法，希望可以摆脱后端的束缚大展身手
（当然你需要跟后端先约定好idl数据结构，否则就是挖坑自己跳了）
### 使用方法
1. 将idl的数据结构补充在idl_config.txt文件中（记得要补充完整
2. 直接运行generateJson.kts文件
3. 输入目标数据，例如xxxResponse，就能输出xxxResponse结构的json文本了（并且帮你随机生成数据，不满意的话可以自己修改一下

### 演示
我这里使用的是AndroidStudio

![image](https://github.com/Y-Richer/idl2json/assets/45263426/6561c36f-3739-4bc5-8900-0cb2433398c3)
