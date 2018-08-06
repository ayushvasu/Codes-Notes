<flink path>/current/bin/start-local.sh

#>jps
#Output should be
#6740 Jps
#6725 JobManager


#run example
<flink path>/current/bin/flink run current/examples/batch/WordCount.jar -input /home/impadmin/Flink/command.sh -output /home/impadmin/Flink/output.txt
