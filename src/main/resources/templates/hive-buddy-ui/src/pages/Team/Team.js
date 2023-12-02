import React from 'react'
import itemData from './const/imageList'
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import IconButton from '@mui/material/IconButton';
import InfoIcon from '@mui/icons-material/Info';
import {Container, Typography, Box} from "@mui/material";
import {PageStyles} from "../consts/pageStyles";


const Team = () => {
    return (
        <Container sx={PageStyles.containerImages}>
            <Box sx={PageStyles.typographyTitles}>
                <Typography variant="h4" >Our team</Typography>
            </Box>
            <ImageList sx={PageStyles.imageList} variant="woven" cols={3} gap={8}>
                {itemData.map((item) => (
                    <ImageListItem key={item.img}>
                        <img
                            srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                            src={`${item.img}?w=248&fit=crop&auto=format`}
                            alt={item.title}
                            loading="lazy"
                        />
                        <ImageListItemBar
                            title={item.title}
                            subtitle={item.author}
                            actionIcon={
                                <IconButton
                                    sx={{color: 'rgba(255, 255, 255, 0.54)'}}
                                    aria-label={`info about ${item.title}`}
                                >
                                    <InfoIcon/>
                                </IconButton>
                            }
                        />
                    </ImageListItem>
                ))}
            </ImageList>
        </Container>
    )
}
export default Team
