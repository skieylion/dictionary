$0.addEventListener("paste", event => { for(const item of event.clipboardData.items) {
if(item.type.startsWith("image")) {
  var blob = item.getAsFile();
  var reader = new FileReader();
  reader.onload = function(onloadevent) {$1.$server.upload(onloadevent.target.result);};
  reader.readAsDataURL(blob);
} } })