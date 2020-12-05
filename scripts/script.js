const image = document.getElementById("image");   // 上传的图片
const imageText = document.getElementById("image-text");    // 图片名
// 上传图片
function uploadImage() {
  var xmlhttp;
  if (window.XMLHttpRequest) {    // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  }
  else {    // code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }

  xmlhttp.onreadystatechange = function () {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {// 请求已完成，且响应已就绪
      // responseText为上传的图片在服务器上的位置
      image.src = xmlhttp.responseText;
      image.style.marginTop = 0;
      imageText.style.display = "none";
    }
  }
  // 这里为UsersServlet在服务器上的位置，action=updateImg
  xmlhttp.open("GET", "/usersServlet?action=updateImg", true);
  xmlhttp.send();
}


const analyzeButton = document.getElementById("analyze-btn");   // 分析按钮
const analyzing = document.getElementById("analyzing");   // 识别中模块
const result = document.getElementById("result");   // 识别结果模块
const resultName = document.getElementById("result-name");   // 作品名
const resultAuthor = document.getElementById("result-author");  // 作者

// 识别按钮
function analyzingEvent() {
  var xmlhttp;
  if (window.XMLHttpRequest) {    // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  }
  else {    // code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }

  xmlhttp.onreadystatechange = function () {
    if (xmlhttp.status == 404) {    // 失败提示
      showResult("很抱歉，我们似乎遇到了一些问题，识别已中断。", "您可以尝试重新识别。")
    } else if (xmlhttp.readyState >= 0 && xmlhttp.readyState <= 3) {     // 识别中
      result.style.display = "none";
      analyzing.style.display = "block";
      analyzeButton.setAttribute("disabled", true);    
    } else if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {    // 请求已完成，且响应已就绪
      let artworkInfo = xmlhttp.responseText.split('$');    // responseText为作品与作者信息，用$(暂定)分开
      showResult(artworkInfo[0], artworkInfo[1]);
    }
  }
  // 这里为UsersServlet在服务器上的位置，action=analyze
  xmlhttp.open("GET", "/usersServlet?action=analyze", true);
  xmlhttp.send();
}

analyzeButton.addEventListener("click", analyzingEvent);

function showResult(name, author) {
  resultName.textContent = name;  // result-name改为识别的作品名
  resultAuthor.textContent = author;  // result-name改为对应的作者
  analyzing.style.display = "none";
  result.style.display = "block";
  analyzeButton.removeAttribute("disabled");
}
