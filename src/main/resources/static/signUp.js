let currentTheme = localStorage.getItem("theme");
console.log(currentTheme);
if(currentTheme==="light-theme"){
    document.body.classList.toggle("light-theme");
}


let nameError = document.getElementById('name-error');
let passError = document.getElementById('passwrd-error');
let emailError = document.getElementById('email-error');
let nickError = document.getElementById('nick-error');
let submitError = document.getElementById('submit-error');

function validateName(){
    let name = document.getElementById('username').value;

    if(!name.match(/^[A-Za-z0-9]*$/)){
        nameError.innerHTML = '<i class="fa-solid fa-xmark" style="color:red"></i>';
        document.getElementById('username').style.borderBottomColor = "red";
        return false;
    }
    if(name.length >= 15 || name.length <= 3 ){
        nameError.innerHTML = '<i class="fa-solid fa-xmark" style="color:red"></i>';
        document.getElementById('username').style.borderBottomColor = "red";
        return false;
    }
    nameError.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
    document.getElementById('username').style.borderBottomColor = "green";
    return true;
}
function validatePasswrd(){
    let passwrd = document.getElementById('password').value;

    if(!passwrd.match(/^[A-Za-z0-9]*$/)){
        passError.innerHTML = '<i class="fa-solid fa-xmark" style="color:red"></i>';
        document.getElementById('password').style.borderBottomColor = "red";
        return false;
    }
    if(passwrd.length >= 20 || passwrd.length <= 9 ){
        passError.innerHTML = '<i class="fa-solid fa-xmark" style="color:red"></i>';
        document.getElementById('password').style.borderBottomColor = "red";
        return false;
    }
    passError.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
    document.getElementById('password').style.borderBottomColor = "green";
    return true;
}

function validateEmail(){
    let email = document.getElementById('email').value;

    // if(email.length ==0){
    //     emailError.innerHTML = '이메일을 입력하세요.';
    //     return false;
    // }
    // if(!email.match(/^[A-Za-z\._\-[0-9]*[@][A-Za-z]*[\.][a-z]{2,4}$/)){
    //     emailError.innerHTML = '올바르지 않은 입력입니다.';
    //     return false;
    // }
    if(!email.match(/^[A-Za-z\._\-[0-9]*[@][A-Za-z]*[\.][a-z]{2,4}$/)){
        emailError.innerHTML = '<i class="fa-solid fa-xmark" style="color:red"></i>';
        document.getElementById('email').style.borderBottomColor = "red";
        return false;
    }
    emailError.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
    document.getElementById('email').style.borderBottomColor = "green";
    return true;
}
function validateNick(){
    let nick = document.getElementById('nickname').value;
    // let required = 10;
    // let left = required - nick.length;

    // if(left>0){
    //     nickError.innerHTML = left + ' 자를 더 입력하세요.';
    //     return false;
    // }
    if(!nick.match(/^[A-Za-z0-9]*$/)){
        nickError.innerHTML = '<i class="fa-solid fa-xmark" style="color:red"></i>';
        document.getElementById('nickname').style.borderBottomColor = "red";
        return false;
    }
    if(nick.length >= 12 || nick.length <= 1 ){
        nickError.innerHTML = '<i class="fa-solid fa-xmark" style="color:red"></i>';
        document.getElementById('nickname').style.borderBottomColor = "red";
        return false;
    }
    nickError.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
    document.getElementById('nickname').style.borderBottomColor = "green";
    return true;
}

function validateForm(){
    if(!validateName() || !validatePasswrd() || !validateEmail() ||!validateNick()){
        submitError.style.display = 'block';
        submitError.innerHTML = '<i style="color:red">작성하지 않은 부분이 있습니다.</i>';
        setTimeout(function(){submitError.style.display = 'none';},3000);
        return false;
    }
}