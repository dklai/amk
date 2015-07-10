# amk
Automatic control mouse and keyboard

## 运行环境
   该程序使用 java 开发， 需要 JRE
   安装软件包中  依赖环境/jre-8u45-windows-x64.exe  (64位系统)
                若是32位系统请自行下载： http://java.com/en/download/manual.jsp

   软件安装后需要设置 JAVA_HOME  和 PATH 环境变量
      请参照  http://jingyan.baidu.com/article/9f7e7ec05bb0ca6f281554c1.html

## 软件指令
  软件的指令文件为 amk.txt

  指令文件注释
     // 或 ＃ 后的信息都是注释信息

  ### 支持如下指令：
  - 	定时  等效指令：TIMEAT      放在第一个有效指令位置，在指定时刻执行指令文件
  - 	延迟  等效指令：DELAY       延迟多少毫秒执行下一个指令
  - 	移动  等效指令：MOVE        鼠标移动到什么位置
  - 	单击  等效指令：CLICK       单击指定位置 或 当前位置
  - 	双击  等效指令：DCLICK      双击指定位置 或 当前位置
  - 	输入  等效指令：INPUT       输入字符串， 当前只支持 英文字符与英文标点符号
  - 	输出  等效指令：ECHO        屏幕输出指定支付串


## 指令说明
<pre>
名称:	定时 / 时间/ TIMEAT 
  参数:	HH:mm:ss.S / HH:mm:ss   # 小时:分钟:秒.毫秒
  例子:
  	定时 00:00:00.10    # 在凌晨 10 毫秒时 执行程序
   	定时 20:00:00       # 在晚上8点执行程序
</pre>
<pre>
名称:   	延迟 / 休眠 / SLEEP / DELAY 
  参数:  	休眠的毫秒数  /  休眠的秒数s(秒) / 休眠的分钟数m(分钟) / 休眠的分钟数h(小时) 
  例子:
  	延迟  5   			＃ 延迟 5 毫秒
  	延迟  6m  			＃ 延迟 6 分钟
  	延迟  7分钟			＃ 延迟 7 分钟
  	延迟  8h				＃ 延迟 8 小时
  	延迟  9小时			＃ 延迟 9 小时
</pre>
<pre>
名称:	移动 / MOVE
  参数:	x,y 或 x y
  例子:
		移动 800,300    		# 从左上角横向移动 800 像素  纵向移动 300 像素
	 	移动 600 400      	# 从左上角横向移动 600 像素  纵向移动 400 像素
</pre>
<pre>
  名称:	单击 / CLICK
  参数:	x,y 或 x y  或 无
  例子:
  	单击 800,300    		# 从左上角横向移动 800 像素  纵向移动 300 像素  处单击
   	单击 600 400      	# 从左上角横向移动 600 像素  纵向移动 400 像素  处单击
   	单击 				        # 在鼠标当前位置单击
</pre>
<pre>
名称:	双击 / DCLICK
  参数:	x,y 或 x y  或 无
  例子:
    双击 800,300    		# 从左上角横向移动 800 像素  纵向移动 300 像素  处双击
    双击 600 400      	# 从左上角横向移动 600 像素  纵向移动 400 像素  处双击
    双击 				        # 在鼠标当前位置双击

</pre>
<pre>
名称:	输入 / INPUT
  参数:	输入字符串
  例子:
    输入 abcdefg    		# 模拟键盘输入字符串， 字符串中不能包含汉字和 # 
</pre>
<pre>
名称:	输出 / ECHO
  参数:	输出字符串
  例子:
    输出 abcdefg    		# 模拟键盘输出字符串， 字符串中不能包含#    		 	
</pre>
## 示例
<pre>
凌晨0点单击屏幕（838, 479) 坐标位置
	定时  00:00:00
	点击  838 479
	输出  任务完成
</pre>
<pre>
下午 1 点 点击屏幕 (233,489) 坐标位置, 延迟 20 毫秒后 点击 (400, 489)
	定时  13:00:00
	点击  233,489
	延迟  20
	点击  400 489
</pre>
