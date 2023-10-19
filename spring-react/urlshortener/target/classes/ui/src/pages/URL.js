import React, { Component } from "react";

class URL extends Component {
    constructor() {
        super();
        this.state = {
            name: "React"
        };
        this.onValueChange = this.onValueChange.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.formSubmit = this.formSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({
            urladdress: event.target.value,
            decodedURL: this.decode(event.target.value)
        });
    }

    decode(urlValue) {
        // PUT request using fetch with set headers
        const operation = this.state.selectedOption === undefined || this.state.selectedOption === "Encode" ? 'update' : 'decode';
        const urlToCall = 'http://localhost:8080/urlshortener/' + operation + '/';
        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ urlValue })
        };
        fetch(urlToCall, requestOptions)
            .then(async response => {
                const data = await response.json();

                // check for error response
                if (!response.ok) {
                    // get error message from body or default to response status
                    const error = (data && data.message) || response.status;
                    return Promise.reject(error);
                }

                this.setState({
                    decodedURL: data.shortenedURL
                });
            })
            .catch(error => {
                this.setState({
                    decodedURL: error
                });
            });
    }

    onValueChange(event) {
        this.setState({
            selectedOption: event.target.value
        });
    }

    formSubmit(event) {
        event.preventDefault();
        this.decode(this.state.urladdress);
    }

    render() {
        return (
            <form onSubmit={this.formSubmit}>
                <div>
                    <input
                        type="text"
                        name="url"
                        style={{ width: "1800px" }}
                        maxLength={50000}
                        placeholder="Enter url here..."
                        value={this.state.urladdress}
                        onChange={this.handleChange}
                    />
                </div>
                <div>
                    <input
                        type="text"
                        disabled
                        style={{ width: "1800px" }}
                        maxLength={50000}
                        name="decodedUrl"
                        placeholder="decoded url here..."
                        value={this.state.decodedURL}
                    />
                </div>
                <div className="radio">
                    <label>
                        <input
                            type="radio"
                            value="Encode"
                            checked={this.state.selectedOption === "Encode"}
                            onChange={this.onValueChange}
                        />
                        Encode
                    </label>
                </div>
                <div className="radio">
                    <label>
                        <input
                            type="radio"
                            value="Decode"
                            checked={this.state.selectedOption === "Decode"}
                            onChange={this.onValueChange}
                        />
                        Decode
                    </label>
                </div>
                <div>
                    Selected option is : {this.state.selectedOption}
                </div>
                <button className="btn btn-default" type="submit">
                    Submit
                </button>
            </form>
        );
    }
}

export default URL;