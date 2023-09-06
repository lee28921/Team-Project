<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>

<section class="modify">
    <h3>글쓰기</h3>
    <article>
        <form action="/Farmstory2/board/modify.do" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="fno" value="${article.fileDto.fno}">
        	<input type="hidden" name="no" value="${article.no}">
        	<input type="hidden" name="group" value="${group}">
        	<input type="hidden" name="cate" value="${cate}">
        	<input type="hidden" name="writer" value="${sessUser.uid}">
        	<input type="hidden" name="ofile" value="${article.fileDto.ofile}">
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="${article.title}"/></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content">${article.content}</textarea>                       
                    </td>
                </tr>
                <tr>
                    <td>첨부</td>
                    <td>
                    	<input type="file" id="fileInput" name="file"/>
                    	<span id="fileResult">파일명 : ${article.fileDto.ofile}</span>
                    </td>
                </tr>
            </table>
            <div>
                <a href="/Farmstory2/board/view.do?group=${group}&cate=${cate}&no=${article.no}" class="btnCancel">취소</a>
                <input type="submit"  class="btnWrite" value="작성완료">
            </div>
        </form>
    </article>
</section>
<!-- 내용 끝 -->

         </article>
     </section>

 </div>
<%@ include file="../_footer.jsp" %>