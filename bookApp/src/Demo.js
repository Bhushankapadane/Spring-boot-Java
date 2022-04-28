import React, { PureComponent } from 'react';
// import { withRouter } from 'react-router-dom';

class PassToMeMyRouterHistory extends PureComponent {

  redirectToHome = () => {
   const { history } = this.props;
   if(history) history.push('/home');
  }

  render() {
    const { history } = this.props;

    return(
      (history)
      ?
        <div onClick={this.redirectToHome}>You can go to Home 🥳</div>
      :
        <div>Oh, we did not get pathname! 🤔</div>
    );
  }

}

export default PassToMeMyRouterHistory;
