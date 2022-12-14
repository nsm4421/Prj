import { Box, Container } from "@mui/system";
import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Button, Modal, Pagination, Stack, Typography } from "@mui/material";
import CreateIcon from '@mui/icons-material/Create';
import DynamicFeedIcon from '@mui/icons-material/DynamicFeed';
import PostCard from './PostCard';
import { useRecoilState } from "recoil";
import { userState } from "../../recoil/user";

const PostList = ()=>{
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPage, setTotalPage] = useState(0);
    const [posts, setPosts] = useState([]);
    const [user, setUser] = useRecoilState(userState);
    
    useEffect(()=>{
        // 서버요청
        const endPoint = `/api/v1/post?page=${currentPage}`    
        axios.get(endPoint, {
            headers:{
                Authorization:user.token??localStorage.getItem("token")
            }
        }).then((res)=>{
            return res.data.result
        }).then((res)=>{
            setCurrentPage(res.pageable.pageNumber);    // 현재 페이지수
            setTotalPage(res.totalPages);               // 전체 페이지수    
            setPosts([...res.content]);                 // 포스팅 정보
        })
        .catch((err)=>{
            console.log(err);
        })
    }, [currentPage])                                   // 페이지수 변경되는 경우 hook 실행

    const handlePage = (e) => {
        setCurrentPage((parseInt(e.target.outerText)??1)-1);
    }

    return (
        <>
        <Container>
            <Box sx={{marginTop:'5vh', display:'flex', justifyContent:'space-between', alignContent:'center'}}>
                <Typography variant="h5" component="h5">
                    <DynamicFeedIcon/> 포스팅     
                </Typography>
                <Box>
                    <Link to="/post/write">
                        <Button variant="contained" color="success" sx={{marginRight:'10px'}}>
                                <CreateIcon sx={{marginRight:'10px'}}/>포스팅 쓰기
                        </Button>
                    </Link>
                </Box>
            </Box>

            {/* 포스팅 */}    
            <Box>                   
                {
                    posts.map((p, i)=>{
                        return (
                            <Box sx={{marginTop:'5vh'}} key={i}>
                                <PostCard post={p}/>
                            </Box>
                        )
                    })
                }
            </Box> 
            
            {/* 페이지 */}
            <Box sx={{justifyContent:"center", display:"flex", marginTop:"5vh"}}>            
                <Pagination count={totalPage} defaultPage={1} boundaryCount={10} color="primary" size="large" sx={{margin: '2vh'}} onChange={handlePage}/>
            </Box>
        </Container>
        </>
    )
}

export default PostList;