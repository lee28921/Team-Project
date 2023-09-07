<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<div id="sub">
    <div><img src="${ctxPath}/images/sub_top_tit2.png" alt="MARKET"></div>
    <section class="market">
        <aside>
            <img src="${ctxPath}/images/sub_aside_cate2_tit.png" alt="장보기"/>

            <ul class="lnb">
                <li class="on"><a href="${ctxPath}/market/list.do">장보기</a></li>
            </ul>
        </aside>
        <article class="list">
            <nav>
                <img src="${ctxPath}/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>

            <!-- 내용 시작 -->
            <p class="sort">
                <a href="./list.do?type=0" class="${type == 0? 'on':'off'}">전체 <c:if test="${type == 0}">(${total})</c:if> |</a>
                <a href="./list.do?type=1" class="${type == 1? 'on':'off'}">과일 |</a>
                <a href="./list.do?type=2" class="${type == 2? 'on':'off'}">야채 |</a>
                <a href="./list.do?type=3" class="${type == 3? 'on':'off'}">곡류</a>
            </p>
            <table border="0">
            	<c:forEach var="product" items="${products}">
	                <tr>
	                    <td>
	                        <a href="${ctxPath}/market/view.do?pNo=${product.pNo}"><img src="${ctxPath}/thumb/${product.thumb1}" class="thumb" alt="제품"></a>
	                    </td>
	                    <td>
							<c:choose>
								<c:when test="${product.type eq 1}">
									과일
								</c:when>
								<c:when test="${product.type eq 2}">
									채소
								</c:when>
								<c:when test="${product.type eq 3}">
									곡물
								</c:when>
							</c:choose>
						</td>
	                    <td><a href="${ctxPath}/market/view.do?pNo=${product.pNo}">${product.pName}</a></td>
	                    <td><strong>${product.price}</strong>원</td>
	                </tr>
                </c:forEach>
            </table>
            <p class="paging">
            	<c:if test="${pageGroupStart lt 1}">
                	<a href="${ctxPath}/market/list.do?pg=${pageGroupStart - 1}"><</a>
                </c:if>
                <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                	<a href="${ctxPath}/market/list.do?pg=${i}" class="${currentPage == i ? 'on':'off' }">[${i}]</a>
                </c:forEach>
                <c:if test="${pageGroupEnd gt lastPageNum}">
                	<a href="${ctxPath}/market/list.do?pg=${pageGroupEnd + 1}">></a>
                </c:if>
            </p>

            <!-- 내용 끝 -->

        </article>
    </section>

</div>
<%@ include file="../_footer.jsp" %>