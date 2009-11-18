﻿<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html xmlns:v="urn:schemas-microsoft-com:vml">  
  <head>  
    <title>XiorkFlow test</title>  
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
  
    <script charset="UTF-8" src="js/XiorkFlowWorkSpace.js" language="javascript"></script>  
    <script src="js/addprocess.js" language="javascript"></script>
    <script type="text/javascript" language="javascript">  
//load all js   
XiorkFlowWorkSpace.build();   
  
function init(){   
  //create ur workflow container   
  var container = new ScrollPanel(document.getElementById("xiorkflowViewer"));   
  container.setClassName("NAME_XIO_UI_FONT NAME_XIO_XIORKFLOW_VIEWER");   
  
  //create XiorkFlow   
  var model = new XiorkFlowModel();   
  // model.setEditable(false);//important   
  	model.setEditable(true);//important   
	var statem=new StateMonitor();
	var state= new StatusLabel();
  var xiorkFlowWrapper = new XiorkFlowWrapper(container, model,statem,state);   
  



var newNodeModel1 = new ForkNodeModel(); 
    newNodeModel1.setText("开始");  
    newNodeModel1.setPosition(new Point(0,0));   
    model.addMetaNodeModel(newNodeModel1);   







var newNodeModel2 = new ForkNodeModel(); 
    newNodeModel2.setText("审核");  
    newNodeModel2.setPosition(new Point(0,0));   
    model.addMetaNodeModel(newNodeModel2);   







var newNodeModel3 = new ForkNodeModel(); 
    newNodeModel3.setText("审批");  
    newNodeModel3.setPosition(new Point(0,0));   
    model.addMetaNodeModel(newNodeModel3);   






var newNodeModel4 = new StartNodeModel();
    newNodeModel4.setText("秘书填单");  
    newNodeModel4.setPosition(new Point(0,0));   
    model.addMetaNodeModel(newNodeModel4);   


  var transitionModel = new TransitionModel(newNodeModel1, newNodeModel2);  

  model.addTransitionModel(transitionModel);  




}   

 

    </script>  
  </head>  
  
  <body onload="init()">  
    <div id="xiorkflowViewer" style="width: 100%;height: 100%;border: #e0e0e0 1px solid;"></div>  
    <div id="message"></div>
  </body>  
</html>  
