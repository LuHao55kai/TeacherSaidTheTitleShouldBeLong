// 上传图片窗口
const image = document.getElementById('image');   // 上传的图片
const imageText = document.getElementById('image-text');    // 图片名

// 测试上传成功
document.getElementById('test-upload-true').addEventListener('click', changeImage);

// 更改图片
function changeImage() {
  image.src = 'images/the-starry-night.jpg';    // image.src改为用户上传的图片的位置
  image.style.marginTop = 0;
  imageText.textContent = 'the-starry-night.jpg'; // imageText.textContent为用户上传的图片的名称
  imageText.style.marginBottom = 0;
}

const analyzeButton = document.getElementById('analyze-btn');   // 分析按钮
const analyzing = document.getElementById('analyzing');   // 识别中模块
const result = document.getElementById('result');   // 识别结果模块
const resultName = document.getElementById('result-name');   // 作品名
const resultAuthor = document.getElementById('result-author');  // 作者

// 识别按钮
function analyzingEvent() {
  result.style.display = 'none';
  analyzing.style.display = 'block';
  analyzeButton.setAttribute('disabled', true);
}

analyzeButton.addEventListener('click', analyzingEvent);

function showResult() {
  resultName.textContent = '作品名：《星月夜The Starry Night》';  // result-name改为识别的作品名
  resultAuthor.textContent = '作者：梵高Vincent van Gogh';  // result-name改为对应的作者

  analyzing.style.display = 'none';
  result.style.display = 'block';
  analyzeButton.removeAttribute('disabled');
}

document.getElementById('test-show-result').addEventListener('click', showResult);  // 测试按钮-识别完成
