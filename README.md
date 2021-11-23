# Virtual TK1 Airport

In order to run the application, kindly follow the below steps:

1) Extract the ZIP file
2) From the project home directory, navigate to root folder where gradlew.bat file exists.
3) Open a new terminal in the project home and run 'gradlew runServerApp' (this will launch the object server)
4) Open a new terminal in the project home and run 'gradlew runClientApp (this will launch one object client, you can launch more clients by open another terminal and execute this command again)

Note: When you launch server at the second time, it might occur the problem highly possible incurred by the port being used. This problem, however, can be easly solved by kill 'javaw.exe' task in the Task Manger -> Details

###### Running screenshot

1. Executing `gradlew runServerApp`

![cmd_UEypRnIuSH.png](https://i.loli.net/2021/11/23/ciFP8AbGue5RMsx.png)

2. Executing `gradlew runClientApp`

![cmd_Cz9jcVZFC7.png](https://i.loli.net/2021/11/23/PiVIEogmMAqTrZb.png)

![login.png](https://i.loli.net/2021/11/23/9iVtjzBklCp3ZTx.png)

![OverviewWindow.png](https://i.loli.net/2021/11/23/fj9I8MxCsLb6BUd.png)


![FlightModify.png](https://i.loli.net/2021/11/23/x7EKChkFvsfASzQ.png)
###### Problem:
Port 10999 is already in use.

![eclipse_UQPR0H2Ooy.png](https://i.loli.net/2021/11/23/lbBF9LUHVtr4pWI.png)


###### Solution:
Kill all tasks named java* as follow 

![Taskmgr_J3YreCqYrv.png](https://i.loli.net/2021/11/23/5PHKvFGic3yBxLn.png)