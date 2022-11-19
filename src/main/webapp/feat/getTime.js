import moment from 'moment';

// 시계 기능입니다.
const timeView = document.getElementById("timeView");

// 쿠키 정보 전처리 과정입니다.
const cookieData = document.cookie;

// Java에서 미리 생성해둔 쿠키 정보입니다.
const userName = cookieData.split("=")
const dateLog = userName[1].split("|")
const timeLog = dateLog[1].slice(0, 8);

// 유저 정보와 마지막 접속일 출력기능입니다.
const userNameDiv = document.getElementById("userName");
userNameDiv.innerText += userName[0];
const userLogDiv = document.getElementById("userLog");
userLogDiv.innerText += dateLog[0]+" " + timeLog;

// 버튼 클릭시 남은 쿠키 시간 정보를 출력합니다.
function pressBtn(){
	const expireTime = moment(timeLog, "HH:mm:ss").fromNow();
	const cookieMin = timeLog.slice(3, 5);
	const cookieSec = timeLog.slice(6, 8);
	let nowMin= moment().format('mm');
	let nowSec= moment().format('ss');
	let expireMin = 10 -  Math.abs(Number(cookieMin) - Number(nowMin));
	let expireSec = Number(cookieSec) - Number(nowSec);

	const timerId = setInterval( ()=>{
		expireSec -=1;
		timeView.innerText += expireMin + ":" + expireSec;
		console.log(expireMin, ":", expireSec);

		if(expireMin < 0) {
			timeView.innerText = "쿠키가 만료되었습니다."
			clearInterval(timerId);
		}
		if(expireMin == 0 && expireSec == 1){
			timeView.innerText = "쿠키가 만료되었습니다."
			clearInterval(timerId);
		}
		if(expireSec < 0) { // 초단위 시간이 0이되었을 때,
			if((expireMin -1) !== 0){ // 분에서 1분을 빼도 만료시간이 종료되지 않는경우
				expireMin -=1;
				}
			expireSec = 60;
			}
		}, 1000)
}
