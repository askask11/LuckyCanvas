/* Editor: Johnson Gao (Jianqing Gao)
 * Date This Project Created: Dec 2019
 * Description Of This Class: This is a script of transferring and processing data retrived from https://thevillageschool.instructure.com
 * and user functions, as well as generating some HTML contents. 
 * The right of sell, or make any subscitiences of this copy are reserved for the author. 
 *  You can use this script for personal study only.
 */
class Assignment
{
    constructor(title, due, grade, possible, mean, high, low, catagory)
    {
        this.title = title;
        this.duedate = due;
        this.grade = grade;
        this.points_possible = possible;
        this.mean = mean;
        this.high = high;
        this.low = low;
        this.catagory = catagory;
    }
    toString()
    {
        return "{ name=" + this.title + ",\n duedate=" + this.duedate + ",\n grade=" + this.grade + ",\n possible points=" + this.points_possible + ",\n mean=" + this.mean + ", \n high=" + this.high + ", \n low=" + this.low + ", catagory=" + this.catagory + " }";
    }

    getPercentage()
    {
        return parseFloat(this.grade / this.points_possible) * 100;
    }

    getMeanPercentage()
    {
        return parseFloat(this.mean / this.points_possible) * 100;
    }
    getLetterGrade()
    {
        var percentage = this.getPercentage();
        var letter;
        if (percentage >= 93)
        {
            letter = "A";
        } else if (percentage >= 90)
        {
            letter = "A-";
        } else if (percentage >= 87)
        {
            letter = "B+";
        } else if (percentage >= 84)
        {
            letter = "B";
        } else if (percentage >= 80)
        {
            letter = "B-";
        } else if (percentage >= 77)
        {
            letter = "C+";
        } else if (percentage >= 74)
        {
            letter = "C";
        } else if (percentage >= 70)
        {
            letter = "C-";
        } else if (percentage >= 63)
        {
            letter = "D";
        } else
        {
            letter = "F";
        }
        return letter;
    }

}
class Weight
{
    constructor(catagory, weight)
    {
        this.catagory = catagory;
        this.weight = weight;
    }

    toString()
    {
        return "{ catagory: " + this.catagory + ", weight: " + this.weight + "}";
    }

}

class DisplayImage
{
    constructor(address, width, height)
    {
        this.address = address;
        this.width = width;
        this.height = height;
    }

    toString()
    {
        return "{ address = " + this.address + ", width = " + this.width + ", height = " + this.height + "}";
    }

    createImgElement()
    {
        var e = document.createElement('img');
        e.src = this.address;
        e.width = this.width;
        e.height = this.height;
        return e;
    }
    createImgElementWithId(id)
    {
        var i = this.createImgElement();
        i.id = id;
        return i;
    }
    createImgString()
    {
        return "<img src=\'" + this.address + "\' style=' width=" + this.width + "px; height=" + this.height + "px;'>";
    }

}



const BACKGROUND_MUSIC = new Audio();
const CLIP = new Audio();
const SRC_CLIP_TICK = "audio/tick.mp3";
const SRC_CLIP_SLEGHRIDEMIAO = "audio/slegh-ridemiao.wav";
const SRC_NYANCAT = "audio/nyancat.wav";
const SRC_KISS = "audio/kiss.wav";
const SRC_APPLUSE = "audio/appluse.mp3";
const SRC_COUNTDOWN1S = "audio/Countdown1sec.wav";
const SRC_CLICKSOUND = "audio/Click Button 2.wav";
const SRC_TINY_BUTTON_PUSH = "audio/Tiny Button Push.wav";
const SRC_WATERPRESS = "audio/waterPress1.wav";
const SRC_ABOVEAVG_FOLDER = "images/above average/";

const SRCSET_CHEERMUSIC_A = [SRC_NYANCAT, SRC_CLIP_SLEGHRIDEMIAO];
const SRCSET_CHEERCLIP_A = [SRC_APPLUSE];
const IMGSET_CONGRAT_A = ["images/conggif.gif", "images/conggif2.gif", "images/conggif3.gif", "images/congimg.jpg"];
const IMGSET_SIDEIMG_A = ["images/cheercat.gif", "images/cheerboy.gif", "images/balonc.gif"];
const IMGSET_ABOVE_AVG = [new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage1.jpg", 242, 141),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage2.jpg", 242, 141),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage3.jpg", 242, 141),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage4.jpg", 166, 125),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage5.jpg", 246, 138),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage6.png", 151, 113),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage7.jpg", 200, 200),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage8.jpg", 104, 102),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage9.jpg", 200, 200),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage10.jpg", 320, 180),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage11.jpg", 215, 143),
    new DisplayImage(SRC_ABOVEAVG_FOLDER + "upaverage12.png", 308, 111)];

var assignments = new Array();
var weightArray = new Array();
var selectWidth = 100;
var assignmentOn = new Assignment();
var countdown = 0;
var expectedGrade = "";
var secLeft;
var doPlayClip = true;
var userName;
//var doPlayMusic = true;



function out(sth)
{
    document.getElementById('result').innerHTML += "<li>" + sth + "</li> \n";
}

function muteUnmuteClip()
{
    doPlayClip = !doPlayClip;
    //gotta change the icon
    if (doPlayClip)
    {
        document.getElementById('soundSettingIcon').src = "images/soundicon16.svg";
        playClip(SRC_CLICKSOUND);
    } else
    {
        document.getElementById('soundSettingIcon').src = "images/mute16.svg";
        CLIP.pause();
        BACKGROUND_MUSIC.pause();
    }

}


/**
 * This function will generate a random integer.
 * @param {type} upperBound The upmost bound of the interger that is going to be generated.
 * @param {type} lowerBound The minimun of generated integer.
 * @returns {Number} The number generated.
 */
function randomInt(upperBound, lowerBound)
{
    return lowerBound + parseInt(Math.random() * (upperBound - lowerBound + 1));
}

function getAssignments()
{
    var ids = new Array();
    var assignments = document.getElementsByClassName('student_assignment');
    var assignmentGroup = new Array();

    //get possible ids
    for (var i = 0; i < assignments.length; i++)
    {
        var realId = assignments[i].id.substring(assignments[i].id.indexOf("_") + 1, assignments[i].id.length);
        if (!isNaN(parseInt(realId)))
        {
            ids[ids.length] = realId;
//            out(realId);
        }
    }

    //main submission format: submission_

    //get the student gradebook from the website.
    for (var i = 0; i < ids.length; i++)
    {
        var submission = document.getElementById("submission_" + ids[i]);
        var average = document.getElementById("grade_info_" + ids[i]);
        var assignment = new Assignment();
        //console.log(i);
        //
        //only run if the assignment is greaded.
        if (submission.classList.contains("assignment_graded"))
        {
            //out("name: " + submission.children[0].children[0].innerHTML);//name
            assignment.title = submission.children[0].children[0].innerHTML.trim();


            // out("catagory: " + submission.children[0].children[1].innerHTML);//catagory
            assignment.catagory = submission.children[0].children[1].innerHTML.trim();

            //out("duedate: " + submission.children[1].innerHTML.trim());//duedate
            assignment.duedate = submission.children[1].innerHTML.trim();


            //On canvas, the grade are randomly wrapped together. Use this loop to unwrap the grade.
            var gradeBunch = submission.children[2].children[0].children;
            for (var j = 0; j < gradeBunch.length; j++)
            {
                if (gradeBunch[j].getAttribute("class") === "tooltip")//the grade must be sourounded by "tooltip"tag
                {

                    //out("grade: " + gradeBunch[j].children[0].innerText.replace("Click to test a different score", ""));//grade
                    assignment.grade = gradeBunch[j].children[0].innerText.replace("Click to test a different score", "").trim();//grade
                }
            }
            //possibel grade
//            out("possible grade: " + submission.children[3].innerHTML);
            assignment.points_possible = submission.children[3].innerHTML.trim();

            //mean grade
//            out("Mean: " + average.children[0].children[0].children[1].children[0].children[0].innerHTML.replace("Mean:", ""));
            assignment.mean = average.children[0].children[0].children[1].children[0].children[0].innerHTML.replace("Mean:", "").trim();

            //highest grade
//            out("High: " + average.children[0].children[0].children[1].children[0].children[1].innerHTML.replace("High:", ""));
            assignment.high = average.children[0].children[0].children[1].children[0].children[1].innerHTML.replace("High:", "").trim();

//            out("Low: " + average.children[0].children[0].children[1].children[0].children[2].innerHTML.replace("Low:", ""));
            assignment.low = average.children[0].children[0].children[1].children[0].children[2].innerHTML.replace("Low:", "").trim();

//            console.log(assignment);
            assignmentGroup[assignmentGroup.length] = assignment;
        }

    }
    outputGradeBook(assignmentGroup);
    return assignmentGroup;
}

/**
 * This appends the gradebook appedix and transfer it to the target side.
 * @param {Array} gradebook
 * @returns {undefined}
 */
function outputGradeBook(gradebook)
{
    var appendix = "";
    var weightgroups = new Array();
    var weightGroupDOM = new Array();

    for (var i = 0; i < gradebook.length; i++)
    {
        var assignment = gradebook[i];
        appendix += "\n\
        " + assignment.title + "∧" + assignment.duedate + "∧" + assignment.grade + "∧" + assignment.points_possible + "∧" + assignment.mean + "∧" + assignment.high + "∧" + assignment.low + "∧" + assignment.catagory + "|\n";
    }
    appendix += "∨";

    if (document.getElementsByClassName('summary')[0] === undefined)
    {
        appendix += "NA";
    } else
    {

        weightGroupDOM = document.getElementsByClassName('summary')[0].children[1].children;


        //
        for (var i = 0; i < weightGroupDOM.length/*Skip the last object*/; i++)
        {
            //get the dom of the currentlu reading weight
            var weightDOM = weightGroupDOM[i];
            var weightObj = new Weight();
            //read catagory and weight from canvas
            weightObj.catagory = weightDOM.children[0].innerHTML.replace("%", "");
            weightObj.weight = weightDOM.children[1].innerHTML.replace("%", "");
            //write in array
            weightgroups[weightgroups.length] = weightObj;
        }


        //Thie is for the weight of each assignment.
        for (var i = 0; i < weightgroups.length - 1; i++)
        {
            var weightObj = weightgroups[i];
            appendix += weightObj.catagory + "∧" + weightObj.weight + "|";
        }

        /**GET USER**/
        appendix += "∨" + document.getElementsByClassName('ic-avatar')[0].children[0].getAttribute("alt");



    }

    //localStorage.setItem("gradebook", appendix);
    console.log("item stored");
    window.location = "luckyMain.html?" + appendix;
}


//////////////////////////////RUN THIS PART ONLY ///////////////////////////////////

function inputGradeBook()
{
    var loc = window.location;
    //console.log(typeof loc);
    loc = loc + "";

    var appendix = loc.substring(loc.indexOf("?") + 1, loc.length);
    console.log(typeof appendix);
    //appendix= appendix.replace("+"," ");
    appendix = decodeURIComponent(appendix);
    //devide the uri into grades and weight
    var splitedInfo = appendix.split("∨");
    //split grade
    var grades = splitedInfo[0].split("|");
    //split weight

    //create arrays
//     assignments = new Array();
//     weightArray = new Array();
    //get detailed sorted grade
    for (var i = 0; i < grades.length; i++)
    {
        var grade = grades[i].split("∧");
        var assignment = new Assignment(grade[0], grade[1], grade[2], grade[3], grade[4], grade[5], grade[6], grade[7]);
        assignments[assignments.length] = assignment;//add into array
    }

    if (splitedInfo[1] === "NA")
    {
    } else
    {
        var weights = splitedInfo[1].split("|");
        //get sorted weight
        for (var i = 0; i < weights.length; i++)
        {
            var weight = weights[i].split("∧");
            var wgightObj = new Weight(weight[0], weight[1]);
            weightArray[weightArray.length] = wgightObj;
        }
    }

    userName = splitedInfo[2];

    console.log("Created For user " + userName);

    console.log("Input done!");
    /*************************** WRITING HTML ****************************/

    //delete later
    //selectWidth=20;
//write grades
    for (var i = 0; i < assignments.length - 1; i++)
    {
        var assignment = assignments[i];
        var title = assignment.title;
        var append = "<li class=\"option\" data-value=" + i + ">" + title + "</li>";
        var length = title.length * 9;
        console.log("title length = " + length);

        if (selectWidth < length)
        {
            selectWidth = length;
        }
        //append = append.replace("%20","&nbsp;");
        document.getElementById('assignmentsSelectOptions').innerHTML += append;
        //document.characterSet="UTF-8";
    }

    document.getElementById('assignmentsSelectBox').style = "min-width:" + selectWidth + "px;";
    // document.getElementById('assignmentsSelectBox').style.length = selectWidth;

    //write weights
//    
}

/**
 * See if user input is a valud input
 * @param {type} doc
 * @returns {undefined}
 */
function isValidInputGradeExpect(doc)
{
    var value = document.getElementById(doc).value;
    var warning = document.getElementById('gradeExpectInputWarning');
    var num = parseFloat(value);
    //console.log("Inputted :" + num);
    if (isNaN(num))
    {
        warning.innerHTML = "Please input a number";
        turnInputIntoWarningStat(doc);
    } else if (num > 100 || num < 0)
    {
        warning.innerHTML = "Please enter a valid number between(0-100) Ex. you expect 90%, enter 90.";
        turnInputIntoWarningStat(doc);
    } else if (num >= 0 && num <= 1)
    {
        //warning
        warning.innerHTML = "Are you sure you entered a correct value? Ex.Expect 90%, enter 90 instead of 0.90";
    } else
    {
        //clear warning
        document.getElementById(doc).style = "";
        warning.innerHTML = "";
    }
}


/**
 * Highlights the document and focus on.
 * @param {type} doc
 * @returns {undefined}
 */
function turnInputIntoWarningStat(doc)
{
    document.getElementById(doc).value = "";
    document.getElementById(doc).focus();
    document.getElementById(doc).style = "background-color:#ff9999;";
}

/////////////////////////////////////////////////////////
/***SHOW GRADE!!!!!!***/
/**
 * 
 * @param {type} maxGrade
 * @param {type} delay
 * @returns {undefined}
 */
function readyShowGrade(maxGrade, delay)
{
//    var indexSelected = parseInt(document.getElementById('img_category_label').getAttribute("data-value"));
//    assignmentOn = assignments[indexSelected];
//    var sec, grd;
    playClip(SRC_CLICKSOUND);
//    /*Fade out main area*/
//    document.getElementById('inputArea').classList.toggle("fadedOut");
//    document.getElementById('dataStatus').classList.toggle("fadedOut");
//    document.getElementById('goodluckTitle').classList.toggle("fadedOut");

    /*Set new area visible*/
//    document.getElementById('displayArea').style = "";
//    document.getElementById('displayArea').classList.toggle("showBlock");

    /*max grade show*/
//    document.getElementById('maxGrade').innerHTML = assignmentOn.points_possible;

    /*Read Countdown & grade*/
//    sec = parseFloat(document.getElementById('delayInput').value);
//    grd = document.getElementById('gradeExpectSelect').getAttribute('data-value');

    countdown = delay;
    expectedGrade = maxGrade; 
}

function showLastDigit(grade)
{
    playClip(SRC_CLICKSOUND);
    if (countdown === 0)
    {
        showLastDigitAction();
    } else
    {
        secLeft = countdown;
        document.getElementById('timeCount').innerHTML = countdown;
        var x = setInterval(function ()
        {
            if (secLeft === 0)
            {
                clearInterval(x);
                showLastDigitAction();
            } else
            {
                count_1();
            }
        }, 1000);
    }
}

function showLastDigitAction(grade)
{
    // document.getElementById('gradeImage').classList.toggle('movedImage');
    document.getElementById('currentGrade').innerHTML += "" + grade.charAt(grade.length - 1);
    document.getElementById('showLastDigitDiv').classList.toggle('fadedOut');
    document.getElementById('showAllText').innerHTML = "Show rest of the grade";
    playClip("audio/waterPress1.wav");
}

function count_1()
{
    secLeft--;
    document.getElementById('timeCount').innerHTML = secLeft;
    playClip(SRC_COUNTDOWN1S);
}
/**
 * 
 * @returns {undefined}
 */
function showAllGrade(grade, goodGrade, okGrade)
{
    playClip(SRC_CLICKSOUND);
    if (countdown === 0)
    {
        showAllGradeAction(grade);
    } else
    {
        secLeft = countdown;
        document.getElementById('timeCount').innerHTML = countdown;
        var x = setInterval(function ()
        {
            if (secLeft === 0)
            {
                clearInterval(x);
                showAllGradeAction();
            } else
            {
                count_1();
            }
        }, 1000);
    }
}

/**
 * This is the father of all methods, regardless of user's grade,this method will be called.
 * @returns {undefined} Nothing
 */
function showAllGradeAction(grade, goodGrade, okGrade)
{
    //display grade
    document.getElementById('currentGrade').innerHTML = grade;
    //fun actions here
    document.getElementById('gradeControlPanel').classList.toggle('fadedOut');
    document.getElementById('msgSection').classList.toggle('showBlock');

    /**Grade Action**/
    if (assignmentOn.getPercentage() >= parseFloat(goodGrade))
    {
        gradeA_Action();
    } else if (assignmentOn.getPercentage() >= parseFloat(okGrade))
    {
        gradeB_Action();
    } else
    {
        gradeC_F_Action();
    }
    document.getElementById('timeCountCountainer').style.setProperty("display", "none");

}

/**
 * Grade A action
 * @returns {undefined}
 */
function gradeA_Action()
{
    console.log("user get an a");
    //congrat mode! 
    var message = "Good job!";
    //playClip(SRC_CLIP_SLEGHRIDEMIAO);
    //change background
    document.getElementById('luckyBody').style = "background-image: url(\"" + IMGSET_CONGRAT_A[randomInt(IMGSET_CONGRAT_A.length - 1, 0)] + "\");";
    //title
    document.getElementById('title').innerHTML += "<br><marquee scrolldelay='30' truespeed='30'><img src='images/congtitle.gif' alt='congrats' class='centralized'><marquee>";
    var s = document.createElement('img');
//    for (var i = 0; i < 10; i++) {
//        console.log("482-side image index: "+randomInt(IMGSET_SIDEIMG_A.length-1,0));
//    }
    var rn = randomInt(IMGSET_SIDEIMG_A.length - 1, 0);
    console.log(rn);
    s.src = IMGSET_SIDEIMG_A[rn];
    s.style = "width: 200px; height: 200px; ";
    s.alt = "cheerboy";
    var s2 = s.cloneNode(false);
    document.getElementById('leftAside').appendChild(s);
    document.getElementById('rightAside').appendChild(s2);

    //a only
    var x = setInterval(function ()
    {
        document.getElementById('currentGrade').style = "color: " + getRandomColor();
    }, 600);

    var imga = document.createElement('img');
    imga.src = "images/gradeastamp.jpg";
    imga.style = "margin-left: 100px; width:130px; height:130px;";
    imga.alt = "gradeastamp";
    document.getElementById('maxGrade').appendChild(imga);

    //process message
    //average determine

    if (assignmentOn.grade > assignmentOn.mean)
    {
        if (assignmentOn.getPercentage() - assignmentOn.getMeanPercentage() > 10)
        {
            message += "Your grade is much higher than average, about " + (assignmentOn.getPercentage() - assignmentOn.getMeanPercentage()) + " % higher! ";
        } else
        {
            message += "Your grade is higher than average!";
        }
        if (assignmentOn.grade === assignmentOn.high)
        {
            message += "You have the highest grade in your class!";
        }
    }

    //expection analysis
//        if (expectedGrade !== "A")
//        {
//            message += "You are probably doing better than you expected! Cheer!";
//        }


    //assignment catagory determine
    for (var i = 0, max = weightArray.length, weightObj = new Weight(); i < max; i++)
    {
        weightObj = weightArray[i];
        if (assignmentOn.catagory === weightObj.catagory)
        {
            if (parseInt(weightObj.weight) >= 20)
            {
                message += " And you Earned it! It worth " + weightObj.weight + " % of your grade! (It belongs to " + weightObj.catagory + " catagory)";
                var nwImg = document.createElement('img');
                nwImg.src = "images/pmgrd.jpg";
                nwImg.style = "width:100px; height:100px; margin-left:100px;";
                nwImg.classList.toggle("centralized");
                document.getElementById('title').appendChild(nwImg);

                if (parseInt(weightObj.weight) >= 50)
                {
                    message += "That's <strong class= 'red'> more than a half!</strong>";

                }
            } else
            {
                message += "This assignment can possible increase your grade";
            }
            i = weightArray.length;
        }
    }

    //user agent determine

    switch (userName)
    {
        case "Di SUN":

            message += "<br><img src='images/heart.gif'>" + "<br><center>小太阳干的漂亮！</center>";

            playClip(SRC_KISS);
        case "Jianqing GAO":
            SRCSET_CHEERMUSIC_A[SRCSET_CHEERMUSIC_A.length] = "audio/ouye.mp3";
        default:
            playClip(SRC_APPLUSE);
            break;

    }

    //if this is a major grade, do a major action.

    var src = SRCSET_CHEERMUSIC_A[randomInt(SRCSET_CHEERMUSIC_A.length - 1, 0)];
    console.log("music src " + src);
    playClip(src);

//        for (var i = 0, max = 20; i < max; i++) {
//            console.log("cheermusic index = " + randomInt(SRCSET_CHEERMUSIC_A.length-1,0 ));
//        }



    document.getElementById('msgSection').innerHTML = message;

}


//const for b
const IMG_B_BACKGROUND_FOLDER = "images/B bg/";
const IMGSET_BG_B = ["images/B bg/aerial-photo-of-mountain-surrounded-by-fog-733174.jpg",
    IMG_B_BACKGROUND_FOLDER + "atmosphere-background-bright-clouds-19670.jpg",
    IMG_B_BACKGROUND_FOLDER + "pexels-photo-1546901.jpeg",
    IMG_B_BACKGROUND_FOLDER + "sunset.jpg",
    IMG_B_BACKGROUND_FOLDER + "wall-bricks-220182.jpg"
];
//const declaer
const TITLE_IMGSET_B = ["goodjob"];
const GOOD_JOB_B_FOLDER = "images/goodjob/";
const IMG_TITLES_B
        = [new DisplayImage(GOOD_JOB_B_FOLDER + "goodjob.jpg", 110, 110),
            new DisplayImage(GOOD_JOB_B_FOLDER + "goodjob2.jpg", 150, 127),
            new DisplayImage(GOOD_JOB_B_FOLDER + "goodjob3.jpg", 222, 154),
            new DisplayImage(GOOD_JOB_B_FOLDER + "goodjob4.jpg", 167, 167),
            new DisplayImage(GOOD_JOB_B_FOLDER + "goodjob5.png", 145, 208),
            new DisplayImage(GOOD_JOB_B_FOLDER + "goodjob6.jpg", 185, 185),
            new DisplayImage(GOOD_JOB_B_FOLDER + "goodjob7.jpg")];
const SRCSET_BGMUSIC_B = ["audio/Apologize.mp3", "audio/alwaysWM.mp3", "audio/Annie's Dream.mp3"];

/*
 * Call this when user get an B.
 * @returns {undefined}
 */
function gradeB_Action()
{
    var massage = "good job!";
    //CHANGE BG
    document.getElementById('luckyBody').style = "background-image: url(\"" + IMGSET_BG_B[randomInt(IMGSET_BG_B.length - 1, 0)] + "\");";
    //change title image
    document.getElementById('title').appendChild(IMG_TITLES_B[randomInt(IMG_TITLES_B.length - 1, 0)].createImgElement());
    //get a stamp
    var img = document.createElement('img');
    img.src = "images/grade stamps/Grade B.jpg";
    img.style = "width:87px;height:87px;margin-left:60px;";
    img.alt = "Grade b STAMP 659";
    document.getElementById('maxGrade').appendChild(img);

    /////////////////compare to class average  //////////////////
    if (assignmentOn.getPercentage() > assignmentOn.getMeanPercentage())
    {
        massage += " Although you are not an A, your grade is still higher than most of people in your class!";
        document.getElementById('title').appendChild(IMGSET_ABOVE_AVG[randomInt(IMGSET_ABOVE_AVG.length, 0)].createImgElement());
    }
    if (assignmentOn.grade === assignmentOn.high)
    {
        massage += "<strong class='red'> This one must be a tough one, no one got above 90! You are the king in the class!</strong>";
    }

    ///////////user decide////////////

    if (userName === "Di SUN")
    {
        massage += "<span class='chineseArtFont'>加油小太阳！下次你一定能做的更好！</span>";
        massage += "<img src='images/bbycheer.gif' title='加油sunny！'>";
    } else
    {
        massage += "If you are not satisified with your current grade, don't be too sad next time aim for an A! People grow step-by-step";
    }

    ///////////PLAY MUSIC///////////
    //Randomly play a music for a B/B+ grade;
    playClip(SRCSET_BGMUSIC_B[randomInt(SRCSET_BGMUSIC_B.length - 1, 0)]);

    if (document.getElementById('gradeExpectSelect').getAttribute('data-value') === "Minor")
    {
        massage += "This is just a minor assignment, geniuses all make mistakes! Just clean up and go ahead!";
    }



    document.getElementById('msgSection').innerHTML = massage;
}

const FOLDER_BG_C_F = "images/CF bg/";

const IMGSRCS_BG_C_F = ["bluabs.jpg", "bluabs2.jpg", "brown.jpg", "grey.jpg", "greyMountain.jpg", "greyWater.jpg", "greyWood.jpg", "shock.jpg",
    "shock2.jpg", "shock3.jpg", "shock4.jpg", "wood.jpg"];

const SRCSET_BGMUSIC_C_F = [/*"audio/God Father.mp3","audio/Songs From A Secret Garden.mp3",*/"audio/God Father 2.mp3"];

/**
 * Call this when user get an C or F.
 * @returns {undefined}
 */
function gradeC_F_Action()
{
    var message;
    message = " You can do better next time! Keep working!";
    //change background
    document.getElementById('luckyBody').style = "background-image: url(\"" + FOLDER_BG_C_F + IMGSRCS_BG_C_F[randomInt(IMGSRCS_BG_C_F.length - 1, 0)] + "\");";
//average

    if (assignmentOn.grade > assignmentOn.mean)
    {
        message += "You are higher than average!";
        document.getElementById('title').appendChild(IMGSET_ABOVE_AVG[randomInt(IMGSET_ABOVE_AVG.length, 0)].createImgElement());
    }

    //play a sad background
    //count down for 2 sec then play final.
    playMusic("audio/shock.wav");
    var t = 2;
    var inner = setInterval(function ()
    {
        t--;
        if (t === 0)
        {
            playMusic(SRCSET_BGMUSIC_C_F[randomInt(SRCSET_BGMUSIC_C_F.length - 1, 0)]);
            clearInterval(inner);
        }
    }, 1000);




    if (document.getElementById('gradeExpectSelect').getAttribute('data-value') === "Minor")
    {
        message += "But it's ok it juse a minor assignment.";
    }
    ///SAD
    message += "<br><center><img src='images/shock and regret.png' alt='shock and regret' title='you can do better next time!' style='background-color:#ffff99; '></center>";

    document.getElementById('msgSection').innerHTML = message;
}





/**
 * This function is for testing only.
 * @returns {undefined}
 */
function inputGradeBookFromJS()
{

    for (var i = 0; i < assignments.length - 1; i++)
    {
        var assignment = assignments[i];
        var append = "\n\
<tr>\n\
\n\ <td>" + assignment.title + " </td> <td>" + assignment.duedate + "</td> <td>" + assignment.grade + "</td><td>" + assignment.points_possible + "</td><td>" + assignment.mean + "</td><td>" + assignment.high + "</td><td>" + assignment.low + "</td><td>" + assignment.catagory + "</td>  </tr>";
        //append = append.replace("%20","&nbsp;");
        document.getElementById('myGradeBook').innerHTML += append;
        //document.characterSet="UTF-8";

    }
    if (weightArray.length === 0)
    {
        document.getElementsByTagName('body')[0].innerHTML += ("There is no weight for this grade.");
    } else
    {
        for (var i = 0, max = weightArray.length - 1; i < max; i++)
        {
            var weightObj = weightArray[i];
            var append = "<tr> <td>" + weightObj.catagory + "</td><td> " + weightObj.weight + "</td></tr>";
            document.getElementById('weightsTable').innerHTML += append;
        }
    }
}

/**
 * Play a sound clip
 * @param {type} src
 * @returns {undefined}
 */
function playClip(src)
{
    if (doPlayClip)
    {
        CLIP.src = src;
        CLIP.play();
    }
}

/**
 * Play background music
 * @param {type} src
 * @returns {undefined}
 */
function playMusic(src)
{
    if (doPlayClip)
    {
        BACKGROUND_MUSIC.src = src;
        BACKGROUND_MUSIC.play();
    }
}

/**
 * Returns a random color!
 * @returns {String}
 */
function getRandomColor()
{
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++)
    {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
function showPopup(popupToShow)
{
    var popup = document.getElementById(popupToShow);//get the id of the popup to show
    popup.classList.toggle("show");//add an "show" to the end of the class list if it is there, otherwise cancel the class.
}