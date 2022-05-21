import React from 'react';
import { Icon,Form,TextArea,Button } from 'semantic-ui-react'
import './chat-window.css';
import axios from 'axios';
import handle from './wsmain.js';
import IMEvent from './IMEvent.js'
import config from '../../common.js';

class Chat extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      infos: [],
      isLoading: false,
      msgContent: ''
    };

  }
  guid() {
    function S4() {
      return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
  }
  handleMsgChange = (event) => {
    this.setState({
      msgContent: event.target.value
    });
  }

  sendMsg = () => {
    let {to_user,from_user,avatar} = this.props.chatInfo;
    let pdata = {
      id: this.guid(),
      // from_user: from_user,
      toId: to_user,
      from:{
        id:this.state.fromId
      },
      // avatar: avatar,
      msg: this.state.msgContent
    }
    let newList = [...this.state.infos];
    newList.push(pdata);
    this.setState({
      infos: newList
    })
    this.state.client.emitEvent(IMEvent.MSG_TEXT_SEND,JSON.stringify(pdata));
  }
  // componentDidMount = () => {
  //   let {to_user,from_user} = this.props.chatInfo;
  //   axios.post('/chats/info',{
  //     to_user: to_user,
  //     from_user: from_user
  //   }).then(data=>{
  //     this.setState({
  //       infos: data.data.list,
  //       isLoading: true,
  //       client: handle(localStorage.getItem('uid'),(data)=>{
  //         let newList = [...this.state.infos];
  //         newList.push(JSON.parse(data.content));
  //         this.setState({
  //           infos: newList
  //         })
  //       })
  //     });
  //   })
  // }

  componentDidMount = () => {
    let {to_user,from_user} = this.props.chatInfo;
    axios.get('http://192.168.8.128:18081/message',{params:{
        toId: 1002,
        fromId: 1001
      }}).then(data=>{
      this.setState({
        infos: data,
        isLoading: true,
        fromId: from_user,
        client: handle(from_user,(data)=>{
          // client: handle(localStorage.getItem('uid'),(data)=>{
          let newList = [...this.state.infos];
          newList.push(JSON.parse(data));
          this.setState({
            infos: newList
          })
        })
      });
    })
  }

  render() {
    let {username} = this.props.chatInfo;
    let infoList = null;
    if(this.state.isLoading) {
// let currentUser = parseInt(localStorage.getItem('uid'),10);
      let currentUser = parseInt(this.state.fromId,10);
      infoList = this.state.infos.map(item=>{
        return (
            <li key={item.id} className={currentUser===1002? 'chat-info-right':'chat-info-left'}>
              <img src="http://itcast-haoke.oss-cn-qingdao.aliyuncs.com/images/2018/12/08/15442410962743524.jpg" alt=""/>
              <span>{item.msg}</span>
            </li>
        )
      })
    }
    return(
        <div className='chat-window'>
          <div className="chat-window-title">
            <Icon onClick={this.props.hideChat} name='angle left' className='chat-ret-btn' size='large'/>
            <span>{username}</span>
          </div>
          <div className="chat-window-content">
            <ul>{infoList}</ul>
          </div>
          <div className="chat-window-input">
            <Form>
              <TextArea value={this.state.msgContent} onChange={this.handleMsgChange} placeholder='请输入内容...' />
              <Button onClick={this.props.hideChat}>关闭</Button>
              <Button primary onClick={this.sendMsg}>发送</Button>
            </Form>
          </div>
        </div>
    );
  }

}

export default Chat