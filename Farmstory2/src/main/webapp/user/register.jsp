<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/Farmstory2/js/zipcode.js"></script>
<script>
	$(function(){
		
		// 아이디 중복체크
		$('#btnCheckUid').click(function(){
			
			const uid = $('input[name=uid]').val(); // 해당 uid 값 입력
			const jsonData = {
				"uid": uid
			};
			
			$.ajax({
				url:'/Farmstory2/user/checkUid.do',
				type:'GET',
				data: jsonData,
				dataType:'json',
				success:function(data){
					
					if(data.result >= 1){ // 이미 존재하는 아이디라면
						$('.resultId').css('color','red').text('이미 사용중인 아이디 입니다.');
					}else{
						$('.resultId').css('color','green').text('사용 가능한 아이디 입니다.');
					}
				}
				
			});
		}); // CheckUid end
		
		// 닉네임 중복 체크
		$('input[name=nick]').focusout(function(){
			
			const nick = $(this).val();
			
			const jsonData = {
				"nick": nick	
			};
			
			$.ajax({
				url:'/Farmstory2/user/checkNick.do',
				type:'GET',
				data: jsonData,
				dataType:'json',
				success:function(data){
					
					if(data.result >= 1){
						$('.resultNick').css('color','red').text('이미 사용중인 닉네임 입니다.');
					}else{
						$('.resultNick').css('color','green').text('사용 가능한 닉네임 입니다.');
					}
				}
			
			});
		}); // 닉네임 중복 체크 끝
		
		// 이메일 중복 체크
		$('input[name=email]').focusout(function(){
			
			const email = $(this).val();
			
			const jsonData = {
				"email": email	
			};
			
			console.log(email);
			
			$.ajax({
				url:'/Farmstory2/user/checkEmail.do',
				type:'GET',
				data: jsonData,
				dataType:'json',
				success:function(data){
					
					if(data.result >= 1){
						$('.resultEmail').css('color','red').text('이미 사용중인 이메일 입니다.');
					}else{
						$('.resultEmail').css('color','green').text('사용 가능한 이메일 입니다.');
					}
				}
			
			});
		}); // 이메일 중복 체크 끝
		
		
		
		// 휴대폰 중복 체크
		$('input[name=hp]').focusout(function(){
			
			const hp = $(this).val();
			
			const jsonData = {
				"hp": hp	
			};
			
			console.log(hp);
			
			$.ajax({
				url:'/Farmstory2/user/checkHp.do',
				type:'GET',
				data: jsonData,
				dataType:'json',
				success:function(data){
					
					if(data.result >= 1){
						$('#resultHp').css('color','red').text('이미 사용중인 휴대폰번호 입니다.');
					}else{
						$('#resultHp').css('color','green').text('사용 가능한 휴대폰번호 입니다.');
					}
				}
			
			});
		}); // 휴대폰번호 중복 체크 끝
		
	});
</script>
<div id="user">
	<section class="register">
        <form id="formUser" action="/Farmstory2/user/register.do" method="post">
            <table border="1">
                <caption>사이트 이용정보 입력</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력"/>
                        <button type="button" id="btnCheckUid"><img src="./images/chk_id.gif" alt=""></button>
                        <span class="resultId"></span>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <input type="password" name="pass1" placeholder="비밀번호 입력"/>                            
                    </td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td>
                        <input type="password" name="pass2" placeholder="비밀번호 확인 입력"/>
                        <span class="resultPass"></span>
                    </td>
                </tr>
            </table>
            <table border="1">
                <caption>개인정보 입력</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" placeholder="이름 입력"/>
                        <span class="resultName"></span>
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p>공백없이 한글, 영문, 숫자만 입력가능</p>
                        <input type="text" name="nick" placeholder="별명 입력"/>
                        <span class="resultNick"></span>                            
                    </td>
                </tr>
                <tr>
                    <td>E-Mail</td>
                    <td>
                        <input type="email" name="email" placeholder="이메일 입력"/>
                        <span class="resultEmail"></span>
                    </td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td>
                        <input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13" />
                        <span id="resultHp"></span>
                    </td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <div>
                            <input type="text" name="zip" placeholder="우편번호" readonly/>                                
                            <button type="button" class="btnZip" onclick="zipcode()"><img src="./images/chk_post.gif" alt=""></button>
                        </div>                            
                        <div>
                            <input type="text" name="addr1" placeholder="주소를 검색하세요." readonly/>
                        </div>
                        <div>
                            <input type="text" name="addr2" placeholder="상세주소를 입력하세요."/>
                        </div>
                    </td>
                </tr>
            </table>

            <div>
                <a href="/Farmstory2/user/login.do" class="btnCancel">취소</a>
                <input type="submit"  id="btnSubmit" class="btnSubmit" value="회원가입"/>
            </div>    
        </form>
    </section>
</div>
<%@ include file="../_footer.jsp" %>