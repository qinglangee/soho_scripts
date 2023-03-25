
; (function (root, factory) {
  root.fbase = factory();
}(this, function () {

  
  function escapeHtml(htmlStr) {
    return htmlStr
      .replace(/</g, "&lt;")
      .replace(/>/g, "&gt;")

      // 为什么加了下面这些会出错呢，因为有些引号要保留的，不能替换了
      // .replace(/"/g, "&quot;")
      // .replace(/'/g, "&#39;")
      // .replace(/&/g, "&amp;")
      ;

  }

  /**
   * 在 demo 页面中按固定格式组装出一个菜单, 目前是固定地添加到 ul#menu_box 中
   */
  function setupCategory(ele, index) {
    const id = 'category_' + index;
    ele.attr('id', id);
    ele.addClass('category');
    const category = ele.attr('data-category');
    ele.html(category);

    let menu = $(`
      <li class="nav-item">
        <a class="nav-link" href="#">
          <span data-feather="file-text"></span>
        </a>
      </li>`);
    menu.find('a').attr('href', '#' + id);
    menu.find('a').append(category)
    $('#menu_box').append(menu);
  }

  /**
   * 内容菜单生成，并对相关示例代码进行处理。
   * 查找所有的 div.zh-example, 
   * 其中有 data-category 属性的，取出作为标题。 
   * 没有 data-category 的作为内容 div, 取出其中的 .stage 元素，取其 html 作为 code 添加显示出来。 
   * 内容实行扁平化管理，层级上有 data-category 的实际上是包含跟随它的后续 .zh-example，但也都是同级的 div. 
   */
  function initPageContent(callback, codeType='js') {

    const examples = $('.zh-example');
    let index = 0;
    for (let example of examples) {
      index++;
      example = $(example);

      if (example.attr('data-category') != null) {
        setupCategory(example, index);
        continue;
      }

      if (callback != null) {
        callback(example, index, codeType);
      }
    }
  }



  function copyCode(stageId) {
    const clip = navigator.clipboard;
    if (clip == null) {
      alert("can not copy.");
    }

    const stage = $("#" + stageId);
    let html = stage.html();
    html = html.replace(/^\s{8}/gm, '');
    clip.writeText(html);
    console.log('copy ...');
  }

  function createCodeBlock(language, source, index) {

    const codeBox = $("<div></div>");
    const codeToggle = $("<div style='width:30%;background-color:#F0F0F0;' class='text-center'>Code </div>");
    const code = $("<pre style='display:none;'><code class='language-xxxx'></code></pre>");
    $("code", code).attr('class', 'language-' + language);
    $("code", code).html(source);
    codeToggle.on('mouseover', () => code.show());
    code.on("mouseleave", () => code.hide());
    const copyBtn = $("<button class='btn btn-primary btn-sm btn-copy'>Copy</button>");

    // 目前是默认行为 code 都放在 .stage 元素中， 以后扩展的话再说
    const stageId = 'stage_' + index;
    copyBtn.on('click', () => {
      copyCode(stageId);
    });
    codeBox.append(codeToggle);
    codeToggle.append(copyBtn);
    codeBox.append(code);
    return codeBox;
  }

  /**
   * 找出页面块中的代码部分，创建出代码展示部分. 
   * 这个方法可以对只是查找 .stage 中代码并展示的进行统一处理
   * @param {*} example 要被处理的页面块
   * @param {*} index 页面块排的序号
   * @returns 
   */
  function processCode(example, index, codeType) {

    const stage = $(".stage", example);
    stage.attr('id', "stage_" + index);
    if (stage.length == 0) {
      return;
    }
    let html = fbase.escapeHtml(stage.html());
    html = html.replace(/^\s{10}/gm, '');

    const codeBox = fbase.createCodeBlock(codeType, html, index);

    example.append(codeBox);
  }



  return {
    escapeHtml,
    setupCategory,
    initPageContent,
    createCodeBlock,
    processCode,
  }
}));

