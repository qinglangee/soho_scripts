
from flask import Flask, render_template, request, redirect, session
import datetime as dt
import db

app = Flask(__name__)
app.secret_key = b'_lkdiokHlkjl*eoiZKLGUIkreiuh' + str(dt.datetime.now()).encode('utf-8')

@app.route("/")
@app.route("/index")
def index():
    """home page"""
    if 'username' in session:
        count  = request.args.get('count', 0)
        return render_template('index.html', username=session['username'], count = count)
    else:
        return redirect('/login')

@app.route('/login', methods=('GET', 'POST'))
def login():
    """user login"""
    if request.method == 'GET':
        return render_template('login.html')

    
    username = request.form['username']
    password = request.form['password']
    if username == '' or password == '':
        return render_template('login.html', err_msg="Invalid input.")
    

    acc = db.find_user_by_password(username, password)
    if acc is None:
        return render_template('login.html', err_msg="Username or password wrong.")

    session['username'] = username
    return redirect('/index')


@app.route("/logout")
def logout():
    """user logout"""
    if 'username' in session:
        session.pop('username')
    return redirect('/index')


@app.route('/register', methods=('GET', 'POST'))
def register():
    """user register"""
    page = 'register.html'
    if request.method == 'GET':
        return render_template(page)

    username = request.form['username']
    password = request.form['password']
    password2 = request.form['confirm_password']

    # validate input
    if username == '' or password == '' or password2 == '':
        return render_template(page, err_msg="Required field can not be empty.")
    if password != password2:
        return render_template(page, err_msg="Confirm password is not same as password.")

    # check username
    acc = db.find_by_name(username)
    if acc is not None:
        return render_template(page, err_msg="Username already exists.")

    db.save_user({'username': username, 'password': password})

    return render_template(page, success_msg="Register successfylly.")