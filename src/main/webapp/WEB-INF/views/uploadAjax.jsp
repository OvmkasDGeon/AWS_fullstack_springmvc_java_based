<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>2023. 4. 7.오전 10:35:32</title>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
	background: rgba(0, 0, 0, 0.5);
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	max-width: 600px;
}
</style>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<label for="files"><i class="fas fa-folder-plus"></i></label>
		<input type="file" name="files" multiple id="files" >
		<button>submit</button>
	</form>
	<div class="uploadResult">
		<ul>
		</ul>
	</div>
	<div class="bigPictureWrapper">
		<div class="bigPicture">
		</div>
	</div>	
</body>
<script>

	$(function() {
		$(".bigPictureWrapper").click(function(){
			$(".bigPicture").animate({width:0, height:0}, 1000);
			setTimeout(function() {
				$(".bigPictureWrapper").hide();
			},1000)
		})
		function checkExtension(files){
			const MAX_SIZE = 5 * 1024 * 1024;
			const EXCLUDE_EXT = new RegExp("(.*.?)\.(js|jsp|asp|php)");//파일 확장자에대한 업로드 불가 처리
			for(let i in files){
				if(files[i].size >= MAX_SIZE || EXCLUDE_EXT.test(files[i].name)){
					
					return false;
				}
			}
			return true;
		}
		/* $("form :file").change(function(){
			let str = "";
			for(let i = 0; i< this.files.length; i++){
				str +="<p>===============================</p>";
				str +="<p>" +this.files[i].name + "</p>";
				str +="<p>" +this.files[i].type + "</p>";
				str +="<p>" +this.files[i].size + "</p>";
			}
			$("form div").html(str);
		}) */
		$(".uploadResult ul").on("click", ".img-thumb", function(){
			event.preventDefault();
			//alert(param);
			$(".bigPictureWrapper").css("display", "flex").show();
			var param = $(this).find("img").data("src")
			$(".bigPicture")
				.html("<img src='/display?" + param + "'>")
				.animate({width:'100%', height : '100%'}, 1000);
			
		});
		
		$(".uploadResult ul").on("click", ".btn-remove", function(){
			event.preventDefault();
			var file = $(this).data("file");
			console.log(file);
			$.getJSON("/deleteFile?"+file).done(function(data){
				console.log(data);
			});
		});
		
		function showUploadFile(uploadResultArr){
			var str="";
			for(var i in uploadResultArr){
				let obj =uploadResultArr[i];
				obj.thumb = "on";
				var params = new URLSearchParams(obj).toString();
				if(!obj.image){
					str += '<li><a href="/download?' + params +'"><i class="far fa-file"></i> ';
				}else{
					obj.thumb = "off";
					var params2 = new URLSearchParams(obj).toString();
					str += '<li><a class="img-thumb" href=""><img src="/display?' + params +'" data-src="' + params2 + '">';
				}
				str +=  obj.name + '</a> <i class="far fa-times-circle btn-remove" data-file="'+ params+'"></i></li>';
			}
			$(".uploadResult ul").append(str);
		}
		$("form button").click(function() {
			event.preventDefault();
			let files = $(":file").get(0).files;
			console.log(files);
			
			if(!checkExtension(files)){
				alert("조건 불일치");
				return false;//이벤트 자체를 멈춘다
			}
			
			let formData = new FormData();
			for(let i in files){
				formData.append("files",files[i]);//public void uploadAjax(MultipartFile[] files) 의 이름을 따라감
			}
			
			$.ajax({
				url : "/uploadAjax",
				processData : false,
				contentType : false,
				data : formData,
				method : "post",
				success : function(data){
					console.log(data);
					$("form").get(0).reset();//게시글에선 문제가 될 수 있음 전체 form이 reset을 할 수도 있음
					showUploadFile(data);
				}
				
			})
		})
	})
</script>
</html>