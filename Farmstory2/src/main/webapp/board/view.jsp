<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<script>
	
	$(function(){
		
		
		const commentURL = "/Farmstory2/board/comment.do";	
		const formComment = document.getElementById('formComment');
		const commentList = document.getElementsByClassName('commentList')[0];
		
		// 댓글 삭제
		$(document).on('click','.remove',function(e){
			e.preventDefault();
			
			//alert('클릭');
			
			const no = $(this).data('no');
			const article = $(this).parent().parent();
			
			console.log('no : '+no);
			
			const jsonData = {
					"type":"REMOVE",
					"no":no
			}
			
			$.ajax({
				url: commentURL,
				type: 'GET',
				data: jsonData,
				async : true,
				dataType: 'json',
				success : function(data){
					console.log('result2 : '+data.result2);
					
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
				url: commentURL,
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					
					if(data.result > 0) {
						console.log('result1 : '+data.result2);
						// 동적 화면 생성
						const dt = new Date();
						const year = dt.getFullYear().toString().substr(2,4);
						const month = dt.getMonth()+1;
						const date = dt.getDate();
						const now = year + "-" + month + "-" + date;
						
						const article = `<article>
											<span class="nick">${sessUser.nick}</span>
											<span class="date">`+ now +`</span>
											<textarea class="content">`+content+`</textarea>
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
		
		// 댓글 수정
		
		document.addEventListener('click', async function(e){
			
			const article  = e.target.parentNode.closest('article');
			const textarea = article.getElementsByTagName('textarea')[0];
			const remove   = article.getElementsByClassName('remove')[0];
			const cancel   = article.getElementsByClassName('cancel')[0];
			const modify   = article.getElementsByClassName('modify')[0];
			
			// 수정&수정완료
			if(e.target && e.target.classList.value == 'modify'){
				e.preventDefault();
				
				const txt = e.target.innerText;
				
				if(txt == '수정'){
					// 수정모드
					const value = textarea.value;
					textarea.style.border = '1px solid #e4eaec';
					textarea.style.background = '#fff';
					textarea.readOnly = false;
					textarea.focus();
					
					remove.style.display = 'none';
					cancel.style.display = 'inline';
					modify.innerText = '수정완료';
					
				}else if(txt == '수정완료'){
					
					if(!confirm('정말 수정 하시겠습니까?')){
						return;
					}
										
					const no = e.target.dataset['no'];
					const content = textarea.value;
					
					const params = new URLSearchParams({
						'type': 'MODIFY',
						'no': no,
						'content': content
					});
					
					// 데이터 서버 전송
					const response = await fetch(commentURL+"?"+params, {
						method: 'GET'
					});
								
					// 서버 응답 데이터 수신
					const data = await response.json();
					console.log('data : ' + JSON.stringify(data));
					
					if(data.result > 0){
						alert('수정완료 했습니다.');
						
						// 수정모드 해제
						textarea.style.border = 'none';
						textarea.style.background = 'none';
						textarea.readOnly = true;
						
						remove.style.display = 'inline';
						cancel.style.display = 'none';
						modify.innerText = '수정';
						
					}else{
						alert('수정실패 했습니다.');
					}
				}
			}
			
			// 수정취소
			if(e.target && e.target.classList.value == 'cancel'){
				e.preventDefault();

				const value = textarea.dataset['value'];
				console.log('value : ' + value);
				
				// 수정모드 해제
				textarea.style.border = 'none';
				textarea.style.background = 'none';
				textarea.readOnly = true;
				textarea.value = value;
				
				remove.style.display = 'inline';
				cancel.style.display = 'none';
				modify.innerText = '수정';
			}
			
		});
		
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
        	<form action="#" method="post">
	            <span class="nick">${comment.nick}</span>
	            <span class="date">${comment.rdate}</span>
	            <textarea class="content" readonly>${comment.content}</textarea>                        
	            <div>
	            	<c:if test="${sessUser.uid eq comment.writer}">
		                <a href="#" id="btnRemove" class="remove" data-no="${comment.no}">삭제</a>
		                <a href="#" class="cancel" data-no="${comment.no}">취소</a>
		                <a href="#" class="modify" data-no="${comment.no}">수정</a>
	                </c:if>
	            </div>
            </form>
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