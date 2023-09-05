<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>
<!-- 내용 시작 -->
<section class="list">
    <h3>글목록</h3>
    <article>
        <table border="0">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>날짜</th>
                <th>조회</th>
            </tr>
            <c:forEach var="article" items="${articles}">
            <tr>
                <td>${article.no}</td>
                <td><a href="/Farmstory2/board/view.do?group=${group}&cate=${cate}">${article.title}</a>&nbsp;[${article.comment}]</td>
                <td>${article.nick}</td>
                <td>${article.rdate}</td>
                <td>${article.hit}</td>
            </tr>
            </c:forEach>
        </table>
    </article>

    <!-- 페이지 네비게이션 -->
	<div class="paging">
		<c:if test="${pageGroupStart lt 1}">
			<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupStart - 1}" class="prev">이전</a>
		</c:if>
		<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=${i}" class="num ${currentPage == i ? 'current':'' }">${i}</a>
		</c:forEach>
		<c:if test="${pageGroupEnd gt lastPageNum}">
			<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupEnd + 1}" class="next">다음</a>
		</c:if>
	</div>

   <!-- 글쓰기 버튼 -->
    <a href="/Farmstory2/board/write.do?group=${group}&cate=${cate}" class="btnWrite">글쓰기</a>
</section>
          <!-- 내용 끝 -->

         </article>
     </section>

 </div>
<%@ include file="../_footer.jsp" %>