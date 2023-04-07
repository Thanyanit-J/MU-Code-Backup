// Import all node packages
const path = require('path');
const express = require("express");
const router = express.Router();
const dotenv = require('dotenv');
dotenv.config();
const mysql = require('mysql');
const axios = require("axios");
const cors = require("cors");
const Twit = require("twit");
const twitterConfig = require("./twitterConfig");
const Spotify = require("node-spotify-api");
// import twitter secret

// configurations 
const app = express();
const port = process.env.PORT;
app.use(cors({ credentials: true, origin: true }));

// Call youtube API
app.get("/youtube", (req, res) => {
  const keyword = req.query.q;
  console.log("youtube keyword " + keyword);
 
  const youtubeURL = "https://www.googleapis.com/youtube/v3/search";

  // Call api By using axios async/await
  const callYoutube = async () => {
    try {
      return await axios.get(youtubeURL, {
        params: {
          part: "snippet",
          type: "video",
          key: process.env.YT_KEY,
          // search from keyword
          q: keyword,
          maxResults: 1,
        },
      });
    } catch (error) {
      console.error(error);
    }
  };
  //  Send data to /youtube route
  callYoutube().then(result => res.send(result.data.items[0])) 
});

// Call Spotify API
app.get("/spotify", (req, res) => {
  const keyword = req.query.q;
  const songKeyword = keyword + " soundtrack";
  console.log("spotify keyword " + keyword);

  // Create spotify object 
  const spotify = new Spotify({
    id: process.env.SPOT_ID,
    secret: process.env.SPOT_SECRET,
  });
  
  // search playlist from songKeyword
  spotify.search({ type: "playlist", query: songKeyword, limit:1 }, function(err, data) {
    if (err) {
      return console.log('Error occurred: ' + err);
    }
    res.send(data)
  });
});

// Call Twitter API
app.get("/twitter", (req, res) => {
  const keyword = req.query.q + ' movie';
  console.log("twitter keyword " + keyword);

  // Create Twitter object
  const T = new Twit(twitterConfig);
  // Search the tweets from keyword
  T.get(
    "search/tweets",
    {
      q: keyword,
      count: 3,
      result_type: "recent",
    },
    (error, data, response) => {
      console.log(data);
      res.send(data);
    }
  );
});




// Connect to MySQL
var dbConn = mysql.createConnection({
    host: process.env.MYSQL_HOST,
    user: process.env.MYSQL_USERNAME,
    password: process.env.MYSQL_PASSWORD,
    database: process.env.MYSQL_DATABASE
});
dbConn.connect();

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/* PAGE ROUTE */

// DEFAULT ROUTE
// const defaultPage = "adminTool.html";
const defaultPage = "administrators.html"
router.get('/', function (req, res) {
    res.sendFile(path.join(__dirname + '/' + defaultPage));
});
router.get('/' + defaultPage, function (req, res) {
    res.sendFile(path.join(__dirname + '/' + defaultPage));
});

// OTHER PAGE ROUTES

router.get('/addUser.html', function (req, res) {
    res.sendFile(path.join(__dirname + "/addUser.html"));
});

router.get('/accountData.html', function (req, res) {
    res.sendFile(path.join(__dirname + "/accountData.html"));
});

router.get('/administrators.html', function (req, res) {
    res.sendFile(path.join(__dirname + "/administatrors.html"));
});

router.get('/administatorsTool.html', function (req, res) {
    res.sendFile(path.join(__dirname + "/administatorsTool.html"));
});/**/

router.get('/search.html', function (req, res) {
    res.sendFile(path.join(__dirname + "/search.html"));
});

router.get('/log.html', function (req, res) {
    res.sendFile(path.join(__dirname + "/log.html"));
});

router.get('/login.html', function (req, res) {
    res.sendFile(path.join(__dirname + "/login.html"));
});

// Here is the premade code for easy ctrl+c ctrl+v to add additional route.
/*router.get('', function (req, res) {
    res.sendFile(path.join(__dirname + ""));
});/**/

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/* LOGIN, AUTHETICATION */

/**LOGIN:
 * administatorsTool.html
 * 
 * SELECT with username and password
 * If find matching: check the level
 *      If level = 1 (1 is admin): log in success, route to the page
 * 
 * If failed:
 *      return msg for missing input
 *      return msg for account doesn't exist/username or password is incorrect
 *      return msg for this account doesn't have the right to access the site
 */
router.get('/administrators/login/:uname/:pw', function (req, res) {
    let uname = req.params.uname;
    let pw = req.params.pw;
    let sql = "SELECT * FROM account WHERE username = ? AND password = ?";

    // Missing username or password
    if (!uname || !pw) {
        res.statusMessage = 'Missing username or password.';
        return res.status(400).send();
    }
    let tmp;
    dbConn.query(sql, [uname, pw], function (error, results) {
        // Fail to retrieve data, probaby due to the sql error
        if (error) {
            return res.send({
                error: true, message: error.sqlMessage
            });
        }

        if (results.length == 0) {
            res.statusMessage = "Username or password is incorrect.";
            return res.status(401).send();
        }
        else if (results[0].level == '1') {
            tmp = results[0].id;
        }
        else {
            res.statusMessage = "This account doesn't have the right to access this site.";
            return res.status(402).send();
        }
        dbConn.query("INSERT INTO login_log (`log_id`, `log_time`, `user_id`) VALUES (NULL, CURRENT_TIMESTAMP, ?)",
        tmp, function (error, results) {
            console.log("insert new log" + tmp);
            res.redirect("/administatorsTool.html");
            res.status(200).sendFile(path.join(__dirname + "/administatorsTool.html"));
        });
    });
});

// LOGIN
// to search.html
// require account level <= 2 (be a user or admin)
router.get('/login/login/:uname/:pw', function (req, res) {
    let uname = req.params.uname;
    let pw = req.params.pw;
    let sql = "SELECT * FROM account WHERE username = ? AND password = ?";

    // Missing username or password
    if (!uname || !pw) {
        res.statusMessage = 'Missing username or password.';
        return res.status(400).send();
    }
    dbConn.query(sql, [uname, pw], function (error, results) {
        // Fail to retrieve data, probaby due to the sql error
        if (error) {
            return res.send({
                error: true, message: error.sqlMessage
            });
        }

        let tmp;
        if (results.length == 0) {
            res.statusMessage = "Username or password is incorrect.";
            return res.status(401).send();
        }
        else if (results[0].level == '1' || results[0].level == '2') {
            tmp = results[0].id;
        }
        else {
            res.statusMessage = "This account doesn't have the right to access this site.";
            return res.status(402).send();
        }
        dbConn.query("INSERT INTO login_log (`log_id`, `log_time`, `user_id`) VALUES (NULL, CURRENT_TIMESTAMP, ?)",
        tmp, function (error, results) {
            // console.log("insert new log" + tmprs);
            res.redirect("/search.html");
            res.status(200).sendFile(path.join(__dirname + "/search.html"));
            console.log(__dirname);
        });

    });
});

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/* ADMIN ONLY ACTION */

// INSERT
// Register/Create a new account (of any level)
router.post('/addUser/insert', function (req, res) {
    let user = req.body.user;
    let email = req.body.user.email;
    let uname = req.body.user.username;
    let pw = req.body.user.password;
    let fname = req.body.user.firstname;
    let lname = req.body.user.lastname;
    let lv = req.body.user.level;
    if (!user || !email || !uname || !pw || !fname || !lname || !lv) {
        return res.status(400).send({ error: true, message: 'Missing data. Cannot create a new account.' });
    }
    dbConn.query("INSERT INTO account SET ? ", user, function (error, results) {
        if (error) {
            return res.send({
                error: true, message: error.sqlMessage
            });
        }
        console.log(results);
        return res.send({
            error: false, data: results.affectedRows, message: 'Successfully created a new account.'
        });
    });
});/**/

// UPDATE
// Edit existing account BY ID
router.put('/accountData/update/', function (req, res) {
    let id = req.body.user.id;
    let email = req.body.user.email;
    let uname = req.body.user.username;
    let fname = req.body.user.firstname;
    let lname = req.body.user.lastname;
    let user = req.body.user;
    if (!id) {
        return res.status(400).send({ error: true, message: 'Don\'t know who to update.' });
    }
    else if (!user || !uname || !email || !fname || !lname /*|| !lv*/) {
        return res.status(400).send({ error: true, message: 'Missing data. Cannot update an account.' });
    }
    else
        dbConn.query("UPDATE account SET ? WHERE id = ?", [user, id], function (error, results) {
            if (error) {
                return res.send({
                    error: true, message: error.sqlMessage
                });
            }
            return res.send({
                error: false, data: results.affectedRows, message: 'Successfully updated account ID: ' + id
            })
        });
});

// DELETE
// Delete an account BY EMAIL
router.delete('/accountData/delete', function (req, res) {
    let id = req.body.user.id;
    if (!id) {
        return res.status(400).send({ error: true, message: 'Don\'t know who to delete.' });
    }
    else
        dbConn.query('DELETE FROM account WHERE id = ?', id, function (error, results) {
            if (error) {
                return res.send({
                    error: true, message: error.sqlMessage
                });
            }
            return res.send({
                error: false, data: results.affectedRows, message: 'Successfully delete account: ' + id
            });
        });
});

// SELECT using input string
// get all accounts that colply to the search filter
// Empty string input means SELECT ALL
router.get('/accountData/selectuser/', function (req, res) {
    let sql = "SELECT * FROM account";
    dbConn.query(sql, function (error, results) {
        if (error) {
            return res.send({
                error: true, message: error.sqlMessage
            });
        }
        //console.log(results);
        return res.send({
            error: false, data: results, message: 'Successfully retrieved data from DB'
        });
    });
});

// If input is % (aka %25), change it to ''
function valid(query) {
    if (typeof query !== 'undefined' && query) {
        if (query == '%')
            return '';
        return query;
    }
    else
        return '';
}
router.get('/accountData/selectuser/:id/:email/:fname/:lname/:uname/:lv1/:lv2', function (req, res) {
    let id = valid(req.params.id);
    let email = valid(req.params.email);
    let uname = valid(req.params.uname);
    let fname = valid(req.params.fname);
    let lname = valid(req.params.lname);
    let lv1 = valid(req.params.lv1);
    let lv2 = valid(req.params.lv2);

    let sql = 'SELECT * FROM account WHERE (id >= 0) ';
    if (id != '') sql += "AND (id = " + id + ") ";
    sql += "AND ( LOWER(email) LIKE LOWER('%" + email + "%') ) ";
    sql += "AND ( LOWER(username) LIKE LOWER('%" + uname + "%') ) ";
    sql += "AND ( LOWER(firstname) LIKE LOWER('%" + fname + "%') ) ";
    sql += "AND ( LOWER(lastname) LIKE LOWER('%" + lname + "%') ) ";
    if (lv1 == 1 && lv2 == 2) sql += "AND ( level IN (1,2)) ";
    else if (lv1 == 1) sql += "AND (level = " + lv1 + ") ";
    else if (lv2 == 2) sql += "AND (level = " + lv2 + ") ";
    dbConn.query(sql, function (error, results) {
        if (error) {
            return res.send({
                error: true, message: error.sqlMessage
            });
        }
        //console.log(results);
        return res.send({
            error: false, data: results, message: 'Successfully retrieved data from DB', sql: sql
        });
    });
});



// SELECT ALL
router.get('/accountData/users/', function (req, res) {
    let sql = "SELECT * FROM account";
    dbConn.query(sql, function (error, results) {
        if (error) {
            return res.send({
                error: true, message: error.sqlMessage
            });
        }
        return res.send({
            error: false, data: results, message: 'Successfully retrieved data from DB'
        });
    });
});

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
/* LOGIN LOG */

// SELECT
// get all logs that comply with the search filter
// If input is not empty, call this
router.get('/log/getlog/:lid/:ft/:tt/:id', function (req, res) {
    let lid = valid(req.params.lid);
    let ft = valid(req.params.ft);
    let tt = valid(req.params.tt);
    let id = valid(req.params.id);

    let sql = "SELECT * FROM `login_log` JOIN `account` ON account.id = login_log.user_id WHERE (id >= 0)";
    if (lid != '') sql += "AND (log_id = " + lid + ") ";
    if (id != '') sql += "AND (id = " + id + ") ";
    if (ft != '' && ft != '%') sql += "AND (log_time >= '" + ft + "') ";
    if (tt != '' && tt != '%') sql += "AND (log_time <= '" + tt + "') ";
    dbConn.query(sql, function (error, results) {
        if (error) {
            return res.send({
                error: true, message: error.sqlMessage
            });
        }
        //console.log(results);
        return res.send({
            error: false, data: results, message: 'Successfully retrieved data from DB', sql: sql
        });
    });
});

// SELECT ALL
router.get('/log/getlogs/', function (req, res) {
    let sql = "SELECT * FROM `login_log` JOIN `account` ON account.id = login_log.user_id";
    dbConn.query(sql, function (error, results) {
        if (error) {
            return res.send({
                error: true, message: error.sqlMessage
            });
        }
        return res.send({
            error: false, data: results, message: 'Successfully retrieved data from DB'
        });
    });
});

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

app.use(express.json());
app.use(express.static(__dirname + '/public/'));
app.use(express.urlencoded({ extended: true }));
app.use('/', router); // PUT THIS ROLE AFTER app.use(express.urlencoded({extended: true})); or ERROR!

app.listen(port);
console.log("App listening on port " + port + ", DB: " + process.env.MYSQL_DATABASE);