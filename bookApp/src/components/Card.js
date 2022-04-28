import React, { useState } from 'react';
import ModalDemo from './ModalDemo';

function Card({ book }) {
    const [show,setShow] = useState(false);
    const [bookItem,setItem] = useState();

    
    return (
        <>
            { show?  <ModalDemo show={show} item={bookItem} onClose={()=> setShow(false)}/> :
                book.map((item) => {
                    let thumbnail = item.volumeInfo.imageLinks && item.volumeInfo.imageLinks.smallThumbnail;
                    if (thumbnail !== undefined) {
                        return (
                            <>
                                <div className="col" key={item.id}>
                                    <div className="card" onClick={()=> {
                                        setShow(true);
                                        setItem(item);
                                      }} >

                                        <img src={thumbnail} className="card-img-top" alt="" />
                                        <div className="card-body">
                                            <p className="card-title">{item.volumeInfo.title}</p>
                                        </div>
                                    </div>
                                </div>
                                 {/* <Modal show={show} item={bookItem} onClose={()=> setShow(false)}/>  */}
                            </>
                        )
                    }
                })
            }
        </>
    )
}

export default Card
