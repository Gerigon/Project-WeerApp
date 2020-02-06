document.getElementById("downloadButton").addEventListener("click", function() {
    let data = Chengdu+EmeiShan+Ya_an+Kangding_Dardo+Xiao_jin+Mianyang+Langzhong+Ankang_xing_an+wanyuan+Chen_an+Wen_huang_ping+Hanzhong+Fengjie;
    jQuery.ajax({
        type: "POST",
        async: false,
        url: 'fileReader.php',
        dataType: 'json',
        data: {functionname: 'createFile', arguments: data},
})});
