import functools

from flask import Blueprint


bp = Blueprint('auth', __name__, url_prefix='/auth')

@bp.route('/register', methods=('GET', 'POST'))
def register():
    return '<h1>Register</h1>'

@bp.route('/login', methods=('GET', 'POST'))
def login():
    return '<h1>Login</h1>'


@bp.route('/logout')
def logout():
    return '<h1>Logout</h1>'
