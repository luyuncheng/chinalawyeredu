

/**
 * 获取删除照片气泡层
 * @param {} albumid
 * @param {} photoid
 * @param {} i
 */
function getDelFile(id,i) {
//	alert("getDelPhoto");
	$(".webmenu").remove();
	$.ajax({
				type : "POST",
				url : "filedel.action",
				data : "id="+id,
				beforeSend : function() {
				},
				success : function(data) {
//					alert(data);
					$("#p"+i).after(data);
					$.Jxq.delTips('#p'+i,'#p'+i+'_',-60,5,150);
				}
			});
}

/**
 * del删除照片气泡层
 * @param {} i
 */
function removeDelPhoto(i) {
	$("#p"+i+"_").remove();
//	$.Jxq.hideTips('#p'+i+'_');
}

/**
 * 删除页面照片
 * @param {} i
 */
function removePhoto(i){
	$.Jxq.removeTips("#pl"+i,"#p"+i+"_");
}

/**
 * Ajax删除照片数据
 * @param {} photoid
 * @param {} albumid
 * @param {} i
 */
function delphoto(photoid,albumid,i) {
//	alert("delphoto("+photoid+","+albumid+","+i+")");
	$.ajax({
        type: "POST",
        url:"delphoto.action",
        data:"photoid="+photoid+"&albumid="+albumid,
        beforeSend:function(){
        },
        success:function(data){
//        	alert("return message = "+data);
        	if(data=="0")
        		javascript:window.location.href="photolist.action?albumid="+albumid;
        	else
        		alert("删除出错！");
     }});
}