/**
 * 
 */

 $(function(){
		
		// 아이디 중복체크
		$('#btnCheckUid').click(function(){
			
			const uid = $('input[name=uid]').val(); // 해당 uid 값 입력
			
			// 아이디 유효성 검사
			if(!uid.match(reUid)){ 
				$('.resultId').css('color','red').text('유효한 아이디가 아닙니다.');
				isUidOk = false;
				return; // 종료
			}
			
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
						isUidOk = false;
					}else{
						$('.resultId').css('color','green').text('사용 가능한 아이디 입니다.');
						isUidOk = true;
					}
				}
				
			});
		}); // CheckUid end
		
		// 닉네임 중복 체크
		$('input[name=nick]').focusout(function(){
			
			const nick = $(this).val();
			
			// 닉네임 유효성 검사
			if(!nick.match(reNick)){
				$('.resultNick').css('color','red').text('유효한 닉네임이 아닙니다.');
				isNickOk = false;
				return;
			}
			
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
						isNickOk = false;
					}else{
						$('.resultNick').css('color','green').text('사용 가능한 닉네임 입니다.');
						isNickOk = true;
					}
				}
			
			});
		}); // 닉네임 중복 체크 끝
		
		// 이메일 중복 체크
		$('input[name=email]').focusout(function(){
			
			const email = $(this).val();
			
			if(!email.match(reEmail)){
				$('.resultEmail').css('color','red').text('유효한 이메일이 아닙니다.');
				isEmailOk = false;
				return;
			}
			
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
						isEmailOk = false;
					}else{
						$('.resultEmail').css('color','green').text('사용 가능한 이메일 입니다.');
						isEmailOk = true;
					}
				}
			
			});
		}); // 이메일 중복 체크 끝
		
		
		
		// 휴대폰 중복 체크
		$('input[name=hp]').focusout(function(){
			
			const hp = $(this).val();
			
			if(!hp.match(reHp)){
				$('#resultHp').css('color','red').text('유효한 휴대폰번호가 아닙니다.');
				isHpOk = false;
				return;
			}
			
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
						isHpOk = false;
					}else{
						$('#resultHp').css('color','green').text('사용 가능한 휴대폰번호 입니다.');
						isHpOk = true;
					}
				}
			
			});
		}); // 휴대폰번호 중복 체크 끝
		
	});