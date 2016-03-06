function main_start() {

  //var div = $("<div>Hello World</div>");
  //div.appendTo('body');

  $("#b_add").on('click', function () {

    var s = '<rect fill="silver" x="0" y="0" width="100%" height="100%" rx="1em"/>';
    s += '<path fill="none" '
      + 'stroke="black" d="M 227 239 L 328 90 L 346 250 L 201 124 L 410 150 L 228 238" />';
    alert($("#pic")[0]);
  });

}
