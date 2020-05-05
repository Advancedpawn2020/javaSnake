# javaSnake
这是基于swing GUI包实现图形界面的简单Java项目，编译运行SnakeApp.java即可开始游戏。
### 界面预览
![JavaSnake](https://github.com/Advancedpawn2020/javaSnake/blob/master/com/zrkworld/img/snake.png?raw=true)
### 可扩展功能：
1. 积分功能：可以创建得分规则的类（模型类的一部分）， 在GameController的run()方法中计算得分
2. 变速功能：比如加速功能，减速功能，可以在GameController的keyPressed()方法中针对特定的按键设置每一次移动之间的时间间隔，将Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);替换为动态的时间间隔即可
3. 更漂亮的游戏界面：修改GameView中的drawXXX方法，比如可以将食物渲染为一张图片，Graphics有drawImage方法
