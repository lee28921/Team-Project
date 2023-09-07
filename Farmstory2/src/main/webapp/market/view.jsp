<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>

	const price		= ${product.price};
	const delivery	= ${product.delivery};

	window.onload = function(){
		
		const inputCount = document.getElementsByName('count')[0];
		const inputTotal = document.getElementsByName('total')[0];
		const inputFinal = document.getElementsByName('final')[0];
		const totalNode = document.getElementsByClassName('total')[0];
		
		// 상품 수량 변경
		inputCount.addEventListener('change',function(e){
			e.preventDefault();

			let count = e.target.value;
			let total = price * count;
			let finalPrice = total + delivery;
			
			console.log('count : '+count);
			console.log('total : '+total);
			console.log('finalPrice : '+finalPrice);
			
			inputCount.value = count;
			inputTotal.value = total;
			inputFinal.value = finalPrice;
			
			totalNode.innerText = total.toLocaleString()+'원';
			
		});
		
		// 상품구매(주문)
		const formOrder = document.getElementById('formOrder');
		const btnOrder = document.getElementsByClassName('btnOrder')[0];
		btnOrder.onclick = (e)=>{
			e.preventDefault();
			
			// 폼 전송
			formOrder.submit();
		};
		
	};
</script>
<div id="sub">
    <div><img src="${ctxPath}/images/sub_top_tit2.png" alt="MARKET"></div>
    <section class="market">
        <aside>
            <img src="${ctxPath}/images/sub_aside_cate2_tit.png" alt="장보기"/>

            <ul class="lnb">
                <li class="on"><a href="${ctxPath}/market/list.do">장보기</a></li>
            </ul>
        </aside>
        <article class="view">
            <nav>
                <img src="${ctxPath}/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>

            <!-- 내용 시작 -->
            <h3>기본정보</h3>
            <div class="basic">
                <img src="${ctxPath}/thumb/${product.thumb2}" alt="${product.pName}">

                <table border="0">                            
                    <tr>
                        <td>상품명</td>
                        <td>${product.pName}</td>
                    </tr>
                    <tr>
                        <td>상품코드</td>
                        <td>${product.pNo}</td>
                    </tr>
                    <tr>
                        <td>배송비</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${product.delivery > 0}">
                        			<span>${product.delivery}</span>원
	                            	<em>3만원 이상 무료배송</em>
                        		</c:when>
                        		<c:otherwise>
                        			<em>무료배송</em>
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>판매가격</td>
                        <td>${product.price}원</td>
                    </tr>
                    <tr>
                        <td>구매수량</td>
                        <td>
                            <input type="number" name="count" min="1" value="1">
                        </td>
                    </tr>
                    <tr>
                        <td>합계</td>
                        <td class="total">${product.price}원</td>
                    </tr>
                    <!-- 제품 데이터 전송 -->
					<form action="${ctxPath}/market/order.do" method="post">
						<input type="hidden" name="thumb2"   value="${product.thumb2}">
	                	<input type="hidden" name="pName"    value="${product.pName}">
	                	<input type="hidden" name="pNo"      value="${product.pNo}">
	                	<input type="hidden" name="delivery" value="${product.delivery}">
	                	<input type="hidden" name="price"    value="${product.price}">
	                	<input type="hidden" name="count"    value="1">
	                	<input type="hidden" name="total"    value="${product.price}">                
	                	<input type="hidden" name="final"    value="${product.price + product.delivery}">
					</form>
                    <a href="#" class="btnOrder">
                        <img src="${ctxPath}/images/market_btn_order.gif" alt="바로 구매하기"/>
                    </a>

                </table>
            </div>
            <h3>상품설명</h3>
            <div class="detail">
                <img src="${ctxPath}/thumb/${product.thumb3}" alt="">

            </div>

            <h3>배송정보</h3>
            <div class="delivery">
                <p>
                    입금하신 이후 택배송장번호는 SMS(문자서비스)를 통해 고객님께 안내해드립니다.
                </p>
            </div>

            <h3>교환/반품</h3>                  
            <div class="exchange">
                <table border="0">
                    <tr>
                        <td>교환 반품이 가능한 경우</td>
                        <td>
                            <ul>
                                <li>팜스토리 상품에 하자가 있거나 불량인 경우</li>
                                <li>채소, 과일, 양곡등의 식품은 만1일 이내</li>
                                <li>기타 상품은 수령일로부터 영업일 기준 일주일 이내</li>
                                <li>받으신 상품이 표시사항과 다른 경우에는 받으신 날로부터 일주일 이내</li>
                            </ul>
                        </td>
                    </tr>
                    <tr>
                        <td>교환 반품이 불가능한 경우</td>
                        <td>
                            <ul>
                                <li>신선 식품의 경우 단순히 마음에 들지 않는 경우</li>
                                <li>단순 변심으로 상품이 가치가 훼손돼서 판매가 어려운 경우</li>
                            </ul>
                        </td>
                    </tr>
                </table>
            </div>
            <!-- 내용 끝 -->
        </article>
    </section>

</div>
<%@ include file="../_footer.jsp" %>