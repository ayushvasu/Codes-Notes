Step 1 - Update. Open a terminal (CTRL + ALT + T) and type the following sudo command. It is advisable to run this before installing any package, and necessary to run it to install the latest updates, even if you have not added or removed any Software Sources.

$ sudo apt-get update

Step 2 - Installing Java 7.

$ sudo apt-get install openjdk-7-jdk

Step 3 - Install open-ssh server. It is a cryptographic network protocol for operating network services securely over an unsecured network. The best known example application is for remote login to computer systems by users.

$ sudo apt-get install openssh-server

Step 4 - Create a Group. We will create a group, configure the group sudo permissions and then add the user to the group. Here 'hadoop' is a group name and 'hduser' is a user of the group.

$ sudo addgroup hadoop
$ sudo adduser --ingroup hadoop hduser

Step 5 - Configure the sudo permissions for 'hduser'.

$ sudo visudo
Since by default ubuntu text editor is nano we will need to use CTRL + O to edit.

ctrl+O
Add the permissions to sudoers.

hduser ALL=(ALL) ALL
Use CTRL + X keyboard shortcut to exit out. Enter Y to save the file.

ctrl+x
Step 6 - Creating spark directory.

$ sudo mkdir /usr/local/spark

Step 7 - Change the ownership and permissions of the directory /usr/local/spark. Here 'hduser' is an Ubuntu username.

$ sudo chown -R hduser /usr/local/spark
$ sudo chmod -R 755 /usr/local/spark

Step 8 - Creating scala directory.

$ sudo mkdir /usr/local/scala

Step 9 - Change the ownership and permissions of the directory /usr/local/scala. Here 'hduser' is an Ubuntu username.

$ sudo chown -R hduser /usr/local/scala
$ sudo chmod -R 755 /usr/local/scala

Step 10 - Creating /app/spark/tmp directory.

$ sudo mkdir /app/spark/tmp

Step 11 - Change the ownership and permissions of the directory /app/spark/tmp. Here 'hduser' is an Ubuntu username.

$ sudo chown -R hduser /app/spark/tmp
$ sudo chmod -R 755 /app/spark/tmp

Step 12 - Switch User, is used by a computer user to execute commands with the privileges of another user account.

$ su hduser

Step 13 - Change the directory to /home/hduser/Desktop , In my case the downloaded spark-1.6.1-bin-hadoop2.6.tgz file is in /home/hduser/Desktop folder. For you it might be in /downloads folder check it.

$ cd /home/hduser/Desktop/

Step 14 - Untar the spark-1.6.1-bin-hadoop2.6.tgz file.

$ tar xzf spark-1.6.1-bin-hadoop2.6.tgz

Step 15 - Move the contents of spark-1.6.1-bin-hadoop2.6 folder to /usr/local/spark

$ mv spark-1.6.1-bin-hadoop2.6/* /usr/local/spark

Step 16 - Untar the scala-2.10.5.tgz file. In my case the downloaded scala-2.10.5.tgz file is in /home/hduser/Desktop folder. For you it might be in /downloads folder check it.

$ tar xzf scala-2.10.5.tgz

Step 17 - Move the contents of scala-2.10.5 folder to /usr/local/scala

$ mv scala-2.10.5/* /usr/local/scala

Step 18 - Edit $HOME/.bashrc file by adding the spark and scala path.

$ sudo gedit $HOME/.bashrc

$HOME/.bashrc file. Add the following lines

export SCALA_HOME=/usr/local/scala
export SPARK_HOME=/usr/local/spark
export PATH=$SPARK_HOME/bin:$JAVA_HOME/bin:$SCALA_HOME/bin:$PATH

Step 19 - Reload your changed $HOME/.bashrc settings

$ source $HOME/.bashrc

Step 20 - Change the directory to /usr/local/spark/conf

$ cd /usr/local/spark/conf

Step 21 - Copy the spark-env.sh.template to spark-env.sh

$ cp spark-env.sh.template spark-env.sh

Step 22 - Edit spark-env.sh file

$ sudo gedit spark-env.sh

Step 23 - Add the below lines to spark-env.sh file. Save and Close.

export SCALA_HOME=/usr/local/scala
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
export SPARK_WORKER_MEMORY=1g
export SPARK_WORKER_INSTANCES=2
export SPARK_MASTER_IP=127.0.0.1
export SPARK_MASTER_PORT=7077
export SPARK_WORKER_DIR=/app/spark/tmp

# Options read in YARN client mode

export HADOOP_CONF_DIR=/usr/local/hadoop/etc/hadoop
export SPARK_EXECUTOR_INSTANCES=2
export SPARK_EXECUTOR_CORES=2
export SPARK_EXECUTOR_MEMORY=1G
export SPARK_DRIVER_MEMORY=1G
export SPARK_YARN_APP_NAME=Spark

Step 24 - Copy the spark-defaults.conf.template to spark-defaults.conf

$ cp spark-defaults.conf.template spark-defaults.conf

Step 25 - Edit spark-defaults.conf file

$ sudo gedit spark-defaults.conf

Step 26 - Add the below line to spark-defaults.conf file. Save and Close.

spark.master                     spark://127.0.0.1:7077

Step 27 - Copy the slaves.template to slaves

$ cp slaves.template slaves

Step 28 - Edit slaves file.

$ sudo gedit slaves

Step 29 - Add the below line to slaves file. Save and Close.

localhost

Step 30 - Change the directory to /usr/local/spark/sbin

$ cd /usr/local/spark/sbin

Step 31 - Start Master and all Worker Daemons.

$ ./start-all.sh

Apache Spark With YARN Installation on Ubuntu 14.04

Step 32 - The JPS (Java Virtual Machine Process Status Tool) tool is limited to reporting information on JVMs for which it has the access permissions.

$ jps
Once the spark is up and running check the web-ui of the components as described below

http://127.0.0.1:8080/
Apache Spark With YARN Installation on Ubuntu 14.04

Step 33 - Stop Master and all Worker Daemons.

$ ./stop-all.sh
Deploying jobs to YARN

2 Types of Deployment Modes

1) Client Mode

2) Cluster Mode

$ ./bin/spark-submit --class com.WordCount2 --master yarn  --deploy-mode client --executor-cores 1 --num-executors 1 /home/hduser/Desktop/1.6\ SPARK/WordCount.jar
			
$ ./bin/spark-submit --class com.WordCount2 --master yarn 
