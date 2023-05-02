console.log("Reply Module");
var xhr = new XMLHttpRequest();
//xhr.open();
//xhr.send();
//즉시 실행 함수 익명함수의 재귀호출
var replyService = (function() {
  //댓글 추가
	function add(reply, callback, error){
    console.log("add() :: "+ reply)
    console.log(reply);
    $.post( {
      url : cp +"/replies/new",
      data : JSON.stringify(reply),
      dataType : "json",
      contentType : "application/json; charset=utf-8"
    })
    .done(function(data){
      if(callback){
        callback(data);
      }
    })
    .fail(function(xhr){
      console.log(xhr)
    })
  }

  //댓글 단일 조회
	function get(rno, callback){
	    var url = cp +"/replies/" + rno; 
	    console.log(url);
	    $.getJSON(url)
	    .done(function(data) {
	        if(callback){
	          callback(data);
	      }
	    })
	  }
  //댓글 목록 조회
  function getList(param, callback, error){
    // var url = "/replies/list/" + param.bno + (param.rno ? ("/" + param.rno) : ""); 
    var url = cp +"/replies/list/" + param.bno + "/" + (param.rno || ""); 
    // nullish ??를 두개 쓴다
    // var url = "/replies/list/" + param.bno + "/" + (param.rno ?? ""); 
    console.log(url);
//      url : url,  위에 써도됨
//      dataType : "json", $.ajax대신 $.getJSON써도됨
    $.getJSON(url)
    .done( function(data) {
        if(callback){
          callback(data);
      }
    })
    .fail(function(xhr){
    	if(error){
    		error(xhr);
    	}
    })
  }
  //댓글 수정
  function modify(reply, callback, error){
    console.log("modify() :: "+ reply)
    console.log(reply);
    $.ajax( {
      url : cp +"/replies/" + reply.rno,
      method:'put',
      data : JSON.stringify(reply),
      dataType : "json",
      contentType : "application/json; charset=utf-8"
    })
    .done(function(data){
      if(callback){
        callback(data);
      }
    })
    .fail(function(xhr){
      console.log(xhr)
    })
  }
  //댓글 삭제
  function remove(reply, callback, error){
    $.ajax( {
      url : cp +"/replies/" + reply.rno,
      method : 'delete',
      data : JSON.stringify(reply),
      dataType : 'json',
      contentType : "application/json; charset=utf-8"
    })
    .done(function(data){
      if(callback){
        callback(data);
      }
    })
    .fail(function(xhr){
      console.log(xhr)
    })
  }

  return {
    add : add,
    getList : getList,
    get:get,
    remove:remove,
    modify:modify
  };
})();