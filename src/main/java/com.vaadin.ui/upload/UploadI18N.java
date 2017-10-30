/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.ui.upload;

import java.util.List;

/**
 * The I18N helper file for the upload component.
 */
public class UploadI18N {
    private DropFiles dropFiles;
    private AddFiles addFiles;
    private String cancel;
    private Error error;
    private Uploading uploading;
    private List<String> units;

    /**
     * Drop files translations class.
     */
    public static class DropFiles extends SingleMulti {
        @Override
        public DropFiles setOne(String one) {
            super.setOne(one);
            return this;
        }

        @Override
        public DropFiles setMany(String many) {
            super.setMany(many);
            return this;
        }
    }

    public static class AddFiles extends SingleMulti {
        @Override
        public AddFiles setOne(String one) {
            super.setOne(one);
            return this;
        }

        @Override
        public AddFiles setMany(String many) {
            super.setMany(many);
            return this;
        }
    }

    public static class Error {
        private String tooManyFiles;
        private String fileIsTooBig;
        private String incorrectFileType;

        public String getTooManyFiles() {
            return tooManyFiles;
        }

        public Error setTooManyFiles(String tooManyFiles) {
            this.tooManyFiles = tooManyFiles;
            return this;
        }

        public String getFileIsTooBig() {
            return fileIsTooBig;
        }

        public Error setFileIsTooBig(String fileIsTooBig) {
            this.fileIsTooBig = fileIsTooBig;
            return this;

        }

        public String getIncorrectFileType() {
            return incorrectFileType;
        }

        public Error setIncorrectFileType(String incorrectFileType) {
            this.incorrectFileType = incorrectFileType;
            return this;
        }
    }

    public static class Uploading {
        private Status status;
        private RemainingTime remainingTime;
        private Error error;

        public Status getStatus() {
            return status;
        }

        public Uploading setStatus(Status status) {
            this.status = status;
            return this;
        }

        public RemainingTime getRemainingTime() {
            return remainingTime;
        }

        public Uploading setRemainingTime(RemainingTime remainingTime) {
            this.remainingTime = remainingTime;
            return this;
        }

        public Error getError() {
            return error;
        }

        public Uploading setError(Error error) {
            this.error = error;
            return this;
        }

        public static class Status {
            private String connecting;
            private String stalled;
            private String processing;
            private String held;

            public String getConnecting() {
                return connecting;
            }

            public Status setConnecting(String connecting) {
                this.connecting = connecting;
                return this;
            }

            public String getStalled() {
                return stalled;
            }

            public Status setStalled(String stalled) {
                this.stalled = stalled;
                return this;
            }

            public String getProcessing() {
                return processing;
            }

            public Status setProcessing(String processing) {
                this.processing = processing;
                return this;
            }

            public String getHeld() {
                return held;
            }

            public Status setHeld(String held) {
                this.held = held;
                return this;
            }
        }

        public static class RemainingTime {
            private String prefix;
            private String unknown;

            public String getPrefix() {
                return prefix;
            }

            public RemainingTime setPrefix(String prefix) {
                this.prefix = prefix;
                return this;
            }

            public String getUnknown() {
                return unknown;
            }

            public RemainingTime setUnknown(String unknown) {
                this.unknown = unknown;
                return this;
            }
        }

        public static class Error {
            private String serverUnavailable;
            private String unexpectedServerError;
            private String forbidden;

            public String getServerUnavailable() {
                return serverUnavailable;
            }

            public Error setServerUnavailable(String serverUnavailable) {
                this.serverUnavailable = serverUnavailable;
                return this;
            }

            public String getUnexpectedServerError() {
                return unexpectedServerError;
            }

            public Error setUnexpectedServerError(
                    String unexpectedServerError) {
                this.unexpectedServerError = unexpectedServerError;
                return this;
            }

            public String getForbidden() {
                return forbidden;
            }

            public Error setForbidden(String forbidden) {
                this.forbidden = forbidden;
                return this;
            }
        }
    }

    /**
     * Abstract translation class for single and multi mode translations.
     */
    public static abstract class SingleMulti {
        private String one;
        private String many;

        /**
         * Get translation for single upload.
         *
         * @return single upload translation
         */
        public String getOne() {
            return one;
        }

        /**
         * Set translation for single upload.
         *
         * @param one
         * @return
         */
        public SingleMulti setOne(String one) {
            this.one = one;
            return this;
        }

        public String getMany() {
            return many;
        }

        public SingleMulti setMany(String many) {
            this.many = many;
            return this;
        }
    }

    public DropFiles getDropFiles() {
        return dropFiles;
    }

    public UploadI18N setDropFiles(DropFiles dropFiles) {
        this.dropFiles = dropFiles;
        return this;
    }

    public AddFiles getAddFiles() {
        return addFiles;
    }

    public UploadI18N setAddFiles(AddFiles addFiles) {
        this.addFiles = addFiles;
        return this;
    }

    public String getCancel() {
        return cancel;
    }

    public UploadI18N setCancel(String cancel) {
        this.cancel = cancel;
        return this;
    }

    public Error getError() {
        return error;
    }

    public UploadI18N setError(Error error) {
        this.error = error;
        return this;
    }

    public Uploading getUploading() {
        return uploading;
    }

    public UploadI18N setUploading(Uploading uploading) {
        this.uploading = uploading;
        return this;
    }

    public List<String> getUnits() {
        return units;
    }

    public UploadI18N setUnits(List<String> units) {
        this.units = units;
        return this;
    }
}
