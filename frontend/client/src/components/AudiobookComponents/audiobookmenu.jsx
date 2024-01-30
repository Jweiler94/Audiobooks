import React, { useState, useEffect } from "react";
import axios from "axios";

const AudioMenu = () => {
      const [link, setLink] = useState("");
      const [downloadLink, setDownloadLink] = useState("");
      const [author, setAuthor] = useState("");
      const [title, setTitle] = useState("");
      const [year, setYear] = useState(null);
      const [description, setDescription] = useState("");
      const [chapters, setChapters] = useState("");
      const [textLink, setTextLink] = useState("");
      const [length, setLength] = useState("");

      const [booksData, setBooksData] = useState(null);
      const [authorList, setAuthorList] = useState(null);

      useEffect(() => {
            const fetchData = async () => {
                  try {
                        const booksResponse = await axios.get(
                              "https://librivox.org/api/feed/audiobooks/?^all&format=json"
                        );

                        setBooksData(booksResponse.data);
                  } catch(error) {
                        console.error("Error fetching data", error);
                  }
            };

            fetchData();

      }, []);

     


}

export default AudioMenu;