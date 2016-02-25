

  $(document).ready(function(){
            var quoteButton = document.getElementById("quoteButton");
            var boldButton = document.getElementById("boldButton");
            var italicButton = document.getElementById("italicButton");
            var underlineButton = document.getElementById("underlineButton");
            var unorderedListButton = document.getElementById("unorderedListButton");
            var orderedListButton = document.getElementById("orderedListButton");
            var imageButton = document.getElementById("imageButton");
            var linkButton = document.getElementById("linkButton");
            var displayContentsButton = document.getElementById("displayContentsButton");
            var submitButton = document.getElementById("submitButton");
            var frameDocElem = document.getElementById("textEditor");
	    var frameDoc = frameDocElem.contentWindow.document;
	
function doQuote(){
    alert("you pressed the butoon");
}

function doBold(){
	
    frameDoc.execCommand("bold", false, null);
}

function doItalic(){
    frameDoc.execCommand("italic", false, null);
}

function doUnderline(){
    frameDoc.execCommand("underline", false, null);
    
}

function doUnorderedList(){
    frameDoc.execCommand("insertUnorderedList", false, null);
}

function doOrderedList(){
    frameDoc.execCommand("insertOrderedList", false, null);
}

function doImage(){
    var imageLink = prompt("Insert A link");
    
    if(imageLink !=null && imageLink != ""){
    frameDoc.execCommand("insertImage", false, imageLink);
    }
    else{
        alert("No URL ENTERED!");
    }
}

function doLink(){
    var link = prompt("Enter a link url");
    frameDoc.execCommand("createLink", false, link);
}
function getIFrameContents(){
	var text= frameDoc.body.innerHTML;

	return text;
	
}

function getIFrameContentsWithNoHTML(){
	var nohtml= frameDoc.body.innerText;
	return nohtml;

}

function activateIFrame(){
  var iFrame = document.getElementById("textEditor");
 
 var frameDoc = (iFrame.document || iFrame.contentDocument);
 frameDoc.designMode = "on";

}

function doSubmit(){
    var regTextArea =document.getElementById("postContentTextArea");
   
    var noHtmlTextArea = document.getElementById("postContentNoHtml");
    regTextArea.value = getIFrameContents();
    alert(getIFrameContents());
    noHtmlTextArea.value= getIFrameContentsWithNoHTML();
    alert(getIFrameContentsWithNoHTML());
    var formToSubmit = document.getElementById("addPostForm");
    formToSubmit.submit();
}

            activateIFrame();
            quoteButton.addEventListener('click', doQuote);
            boldButton.addEventListener('click', doBold);
            italicButton.addEventListener('click', doItalic);
            underlineButton.addEventListener('click', doUnderline);
            unorderedListButton.addEventListener("click", doUnorderedList);
            orderedListButton.addEventListener("click", doOrderedList);
            imageButton.addEventListener("click", doImage);
            linkButton.addEventListener("click", doLink);
	    displayContentsButton.addEventListener("click", getIFrameContents);
            submitButton.addEventListener("click", doSubmit);
                        
			
		   });