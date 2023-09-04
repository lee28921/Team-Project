<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
            <tr>
                <td></td>
                <td><a href="#">제목</a>&nbsp;[12]</td>
                <td>별명</td>
                <td>2023-11-11</td>
                <td>21</td>
            </tr>
        </table>
    </article>

    <!-- 페이지 네비게이션 -->
   <div class="paging">
       <a href="#" class="prev">이전</a>

       <a href="#"></a>

       <a href="#" class="next">다음</a>
   </div>

   <!-- 글쓰기 버튼 -->
    <a href="/Jboard1/write.jsp" class="btnWrite">글쓰기</a>
</section>
          <!-- 내용 끝 -->

         </article>
     </section>

 </div>
<%@ include file="../_footer.jsp" %>