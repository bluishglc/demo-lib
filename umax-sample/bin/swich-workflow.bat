start "stop-workflow.bat" "stop-workflow.bat"
cd %UMAX_HOME%\umax-sample-workflow\src\main\resources\diagrams
copy ExamineContractProcess.bpmn20.xml .. /Y
copy ..\alternative-flow\ExamineContractProcess.bpmn20.xml . /Y
copy ..\ExamineContractProcess.bpmn20.xml ..\alternative-flow /Y
del ..\ExamineContractProcess.bpmn20.xml
cd %UMAX_HOME%\umax-sample-workflow
mvn clean compile jetty:run -DskipTests=true

