<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<script>
	
	$(function(){
		
		// 댓글 삭제
		$(document).on('click','.remove',function(e){
			e.preventDefault();
			
			//alert('클릭');
			
			const no = $(this).data('no');
			const article = $(this).parent().parent();
			
			console.log('no : '+no);
			
			const jsonData = {
					"kind":"REMOVE",
					"no":no
			}
			
			$.ajax({
				url: '/Farmstory2/comment.do',
				type: 'GET',
				data: jsonData,
				dataType: 'json',
				success : function(data){
					
					if(data.result > 0){
						alert('댓글이 삭제 되었습니다.');
						
						// 화면처리
						article.remove();
						
					}
				}
			});
			
		});
		
		
		// 댓글 쓰기
		const btnComment = document.getElementById('btnComment');
		btnComment.onclick = function(e){
			e.preventDefault();
			
			const parent = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=content]').val();
			const writer = $('#formComment > input[name=writer]').val();
			
			const jsonData = {
				"parent":parent,
				"content": content,
				"writer":writer
			};
			
			console.log('jsonData : '+jsonData);
			
			$.ajax({
				url: '/Farmstory2/comment.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					
					if(data.result > 0) {
					
						// 동적 화면 생성
						const dt = new Date();
						const year = dt.getFullYear().toString().substr(2,4);
						const month = dt.getMonth()+1;
						const date = dt.getDate();
						const now = year + "-" + month + "-" + date;
						
						const article = `<article>
											<span class="nick">${sessUser.nick}</span>
											<span class="date">`+ now +`</span>
											<p class="content">`+content+`</p>
											<div>
												<a href="#" class="remove">삭제</a>
												<a href="#" class="modify">수정</a>
											</div>
										</article>`;
						$('.commentList').append(article);
						alert("댓글이 생성되었습니다.");
						
					}
					
				}
				
			});
			
			
		};
		
		
		
	});

</script>
<jsp:include page="./_aside${group}.jsp"/>
<section class="view">
    <table border="0">
        <caption>글보기</caption>
        <tr>
            <th>제목</th>
            <td><input type="text" name="title" value="${article.title}" readonly/></td>
        </tr>
        <c:if test="${article.file > 0}">
        <tr>
            <th>파일</th>
            <td><a href="/Farmstory2/fileDownload.do?fno=${article.fileDto.fno}">${article.fileDto.ofile}</a>&nbsp;
            <span>${article.fileDto.download}</span>회 다운로드</td>
        </tr>
        </c:if>
        <tr>
            <th>내용</th>
            <td>
                <textarea name="content" readonly>${article.content}</textarea>
            </td>
        </tr>                    
    </table>
    
    <div>
    	<c:if test="${sessUser.uid eq article.writer}">
	        <a href="/Farmstory2/board/delete.do?group=${group}&cate=${cate}&no=${article.no}" class="btn btnDelete">삭제</a>
	        <a href="/Farmstory2/board/modify.do?group=${group}&cate=${cate}&no=${article.no}" class="btn btnModify">수정</a>
        </c:if>
        <a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}" class="btn btnList">목록</a>
    </div>

    <!-- 댓글목록 -->
    <section class="commentList">
        <h3>댓글목록</h3>
		
		<c:forEach var="comment" items="${comments}">
        <article>
            <span class="nick">${comment.nick}</span>
            <span class="date">${comment.rdate}</span>
            <textarea class="content">${comment.content}</textarea>                        
            <div>
            	<c:if test="${sessUser.uid eq comment.writer}">
	                <a href="#" class="remove" data-no="${comment.no}">삭제</a>
	                <a href="#" class="modify">수정</a>
                </c:if>
            </div>
        </article>
		</c:forEach>
		<c:if test="${empty comments}">
        <p class="empty">등록된 댓글이 없습니다.</p>
		</c:if>
    </section>

    <!-- 댓글쓰기 -->
    <section class="commentForm">
        <h3>댓글쓰기</h3>
        <form id="formComment" action="#" method="post">
        	<input type="hidden" name="parent" value="${article.no}">
        	<input type="hidden" name="writer" value="${sessUser.uid}">
            <textarea name="content"></textarea>
            <div>
                <a href="#" class="btn btnCancel">취소</a>
                <input type="submit" id="btnComment" value="작성완료" class="btn btnComplete"/>
            </div>
        </form>
    </section>

</section>
<!-- 내용 끝 -->

         </article>
     </section>

 </div>
<%@ include file="../_footer.jsp" %>